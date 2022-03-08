package com.project.brainstomingbe.security.jwt;

import com.project.brainstomingbe.User.User;
import com.project.brainstomingbe.security.UserDetailsImpl;
import com.project.brainstomingbe.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider{ // 토큰 생성, 검증

    private String secretKey = "ABCA7D35A0B04018B865E0817E1A41374FB06737CF00641E2A781F631B61C9AC";

    //토큰에 저장된 유저 정보를 활용해야 하기 때문에 CustomUserDetatilService 라는 이름의 클래스를 만들고 UserDetailsService를 상속받아 재정의 하는 과정을 진행합니다.
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    // JWT 토큰 생성
    public String createToken(String userPk)
    {
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        // token 발급 후 유효시간 하루
        long tokenValidTime = 24 * 60 * 60 * 1000L;
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
//                // signature 에 들어갈 secret값 세팅  => 사용 중단된 코드 Keys.hmacShakeyFor 사용
//                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()),SignatureAlgorithm.HS256)
                .compact();
    }



    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsServiceImpl.loadUserById(Long.parseLong(this.getUserPk(token)));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // JWT 토큰에서 User 가져오기
    public User getAuthenticationUser(String token){
        UserDetailsImpl userDetails = userDetailsServiceImpl.loadUserById(Long.parseLong(this.getUserPk(token)));
        return userDetails.getUser();
    }

    // JWT 토큰에서 username 조회
    public String getAuthenticationUsername(String token) {
        UserDetails userDetails = userDetailsServiceImpl.loadUserById(Long.parseLong(this.getUserPk(token)));
        return userDetails.getUsername();
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다
    public String resolveToken(HttpServletRequest request) {

        return request.getHeader("token");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}
