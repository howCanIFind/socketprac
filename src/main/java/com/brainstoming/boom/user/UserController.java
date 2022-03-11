package com.brainstoming.boom.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //닉네임 입력
    @PostMapping("/api/nickname")
    public ResponseEntity<?> enterNickname(@RequestBody UserNickRequestDto requestDto) {
        Long userId = userService.register(requestDto);

        return ResponseEntity.ok().body(userId);
    }
}
