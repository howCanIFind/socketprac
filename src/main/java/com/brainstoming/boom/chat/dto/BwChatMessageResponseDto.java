package com.brainstoming.boom.chat.dto;

import com.brainstoming.boom.chat.BwChatMessage;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class BwChatMessageResponseDto {

    private BwChatMessage.MessageType type;

    private String roomId;

    private Long senderId;

    private String sender;

    private String message;

    private String createdAt;

    @Builder
    public BwChatMessageResponseDto(BwChatMessage.MessageType type, String roomId, Long senderId, String sender, String message, String createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.senderId = senderId;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }
}
