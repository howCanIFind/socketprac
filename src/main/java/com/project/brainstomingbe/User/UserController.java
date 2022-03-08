package com.project.brainstomingbe.User;

import com.google.gson.JsonObject;
import com.project.brainstomingbe.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    // 닉네임 입력
    @PostMapping("/api/nickname")
    public Object enterNickname(@RequestBody UserNicknameRequestDto requestDto) {
        Long userId = userService.register(requestDto);

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("token", jwtTokenProvider.createToken(String.valueOf(userId)));
        Boolean b = jwtTokenProvider.validateToken(jsonObj.toString());
        log.info(jsonObj.toString());
        log.info(b.toString());

        return ResponseEntity.ok().body(jsonObj.toString());
    }
}
