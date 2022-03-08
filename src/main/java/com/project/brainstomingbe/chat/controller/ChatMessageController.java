package com.project.brainstomingbe.chat.controller;

import com.project.brainstomingbe.User.User;
import com.project.brainstomingbe.User.UserService;
import com.project.brainstomingbe.chat.ChatMessageRequestDto;
import com.project.brainstomingbe.chat.ChatMessageResponseDto;
import com.project.brainstomingbe.chat.service.ChatMessageService;
import com.project.brainstomingbe.security.jwt.JwtTokenProvider;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@RestController
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public ChatMessageController(ChatMessageService chatMessageService, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.chatMessageService = chatMessageService;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 채팅 메시지를 @MessageMapping 형태로 받는다
    // 웹소켓으로 publish 된 메시지를 받는 곳이다
    @MessageMapping("/api/chat/message")
    public void message(@RequestBody ChatMessageRequestDto messageRequestDto, @Header("token") String token) {

        // 메시지 생성 시간 삽입
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateResult = sdf.format(date);

        // 채팅 메시지 객체 생성
        ChatMessageResponseDto chatMessageResponseDto = new ChatMessageResponseDto();
        chatMessageResponseDto.setType(messageRequestDto.getType());
        chatMessageResponseDto.setRoomId(messageRequestDto.getRoomId());
        chatMessageResponseDto.setMessage(messageRequestDto.getMessage());

        // 로그인 회원 정보를 들어온 메시지에 값 세팅
        User user = jwtTokenProvider.getAuthenticationUser(token);
        chatMessageResponseDto.setUserId(user.getId());
        chatMessageResponseDto.setSender(user.getNickname());
        chatMessageResponseDto.setCreatedAt(dateResult);


        // 웹소켓 통신으로 채팅방 토픽 구독자들에게 메시지 보내기
        chatMessageService.sendChatMessage(chatMessageResponseDto);

        // MySql DB에 채팅 메시지 저장
        chatMessageService.save(chatMessageResponseDto);
    }
}
