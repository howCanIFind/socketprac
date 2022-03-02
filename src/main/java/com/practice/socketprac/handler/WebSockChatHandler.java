package com.practice.socketprac.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.socketprac.dto.ChatMessage;
import com.practice.socketprac.dto.ChatRoom;
import com.practice.socketprac.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
// 삭제        TextMessage textMessage = new TextMessage("Welcome chatting sever~^^ ");
// 삭제       session.sendMessage(textMessage);
        //직렬화
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        //전달받은 메시지에 담긴 채팅방 Id로 발송 대상 채팅방 정보를 조회함.
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        //해당 채팅방에 입장해있는 모든 클라이언트들(Websocket session)에게 타입에 따른 메시지 방송.
        room.handleActions(session, chatMessage, chatService);
    }
}