package com.project.brainstomingbe.chat.controller;

import com.project.brainstomingbe.chat.ChatRoom;
import com.project.brainstomingbe.chat.ChatRoomRequestDto;
import com.project.brainstomingbe.chat.ChatRoomResponseDto;
import com.project.brainstomingbe.security.UserDetailsImpl;
import com.project.brainstomingbe.chat.service.ChatMessageService;
import com.project.brainstomingbe.chat.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/chat")
public class ChatRoomController {

    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatMessageService chatMessageService, ChatRoomService chatRoomService) {
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;
    }

    // 채팅방 생성
    @PostMapping("/rooms")
    public ChatRoomResponseDto ChatRoomResponseDto(
            @RequestBody ChatRoomRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws IOException {
        log.info("requestDto = {}", requestDto);
        requestDto.setUserId(userDetails.getUser().getId());

        ChatRoomResponseDto chatRoom = chatRoomService.createChatRoom(requestDto);
        log.info("responseDto = {}", chatRoom);
        return chatRoom;
    }

    // test용
    @GetMapping("/rooms")
    public String getChatRoom() {
        ChatRoom room = chatRoomService.getRoom(1L);
        return room.getCategory();
    }

}
