package com.brainstoming.boom.chat.dto;

import com.brainstoming.boom.chat.BwChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BwChatMessageRequestDto {

    private BwChatMessage.MessageType type;
    private String roomId;
    private Long userId;
    private String sender;
    private String message;
    private String createdAt;

    @Builder
    public BwChatMessageRequestDto(BwChatMessage.MessageType type, String roomId, Long userId, String sender, String message, String createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.userId = userId;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }

    @Builder
    public BwChatMessageRequestDto(BwChatMessage.MessageType type, String roomId, String sender, String message, String createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.userId = null;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }
}
