package com.brainstoming.boom.chat.controller;

import com.brainstoming.boom.chat.dto.BwChatMessageRequestDto;
import com.brainstoming.boom.chat.dto.BwChatMessageResponseDto;
import com.brainstoming.boom.chat.service.ChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@RestController
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/api/chat/message")
    public void message(@RequestBody BwChatMessageRequestDto requestDto) {

        // 메시지 생성 시간 삽입
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateResult = sdf.format(date);

        BwChatMessageResponseDto bwChatMessageResponseDto = new BwChatMessageResponseDto();

        bwChatMessageResponseDto.setType(requestDto.getType());
        bwChatMessageResponseDto.setRoomId(requestDto.getRoomId());
        bwChatMessageResponseDto.setMessage(requestDto.getMessage());
        bwChatMessageResponseDto.setSender(requestDto.getSender());
        bwChatMessageResponseDto.setCreatedAt(dateResult);

        chatMessageService.sendChatMessage(bwChatMessageResponseDto);

        chatMessageService.save(bwChatMessageResponseDto);
    }
}
