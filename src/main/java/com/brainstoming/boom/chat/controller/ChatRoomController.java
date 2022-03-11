package com.brainstoming.boom.chat.controller;

import com.brainstoming.boom.chat.dto.BwRoomRequestDto;
import com.brainstoming.boom.chat.dto.BwRoomResponseDto;
import com.brainstoming.boom.chat.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/bwRoom")
    public BwRoomResponseDto chatRoomResponseDto (@RequestBody BwRoomRequestDto requestDto) {
        return chatRoomService.bwCreateChatRoom(requestDto);
    }
}
