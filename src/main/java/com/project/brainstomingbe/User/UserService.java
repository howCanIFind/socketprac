package com.project.brainstomingbe.User;

import com.project.brainstomingbe.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("찾는 유저가 없습니다")
        );
    }

    public Long register(UserNicknameRequestDto requestDto) {
        Random random = new Random();
        String username = random.ints(97, 122)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        String randomPassword = random.ints(97, 122)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        String password = passwordEncoder.encode(randomPassword);
        User user = new User(username, password, requestDto.getNickname());
        userRepository.save(user);
        return user.getId();
    }
}
