package com.project.brainstomingbe.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ChatMessageResponseDto {

    private ChatMessage.MessageType type;

    private String roomId;

    private Long userId;

    private String sender;

    private String message;

    private String createdAt;

    public ChatMessageResponseDto(ChatMessage.MessageType type, String roomId, Long userId, String sender, String message, String createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.userId = userId;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }
//    @Builder
//    public ChatMessageResponseDto(ChatMessage.MessageType type, String roomId, Long userId, String sender, String message, String createdAt) {
//        this.type = type;
//        this.roomId = roomId;
//        this.userId = userId;
//        this.sender = sender;
//        this.message = message;
//        this.createdAt = createdAt;
//    }
}
