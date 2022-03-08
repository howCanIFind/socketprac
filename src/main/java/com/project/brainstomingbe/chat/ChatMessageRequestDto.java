package com.project.brainstomingbe.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageRequestDto {

    private ChatMessage.MessageType type;
    private String roomId;
    private Long userId;
    private String sender;
    private String message;
    private String createdAt;

    @Builder
    public ChatMessageRequestDto(ChatMessage.MessageType type, String roomId, Long userId, String sender, String message, String createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.userId = userId;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }
}
