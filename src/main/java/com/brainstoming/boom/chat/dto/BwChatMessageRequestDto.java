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
    private Long senderId;
    private String sender;
    private String message;

    @Builder
    public BwChatMessageRequestDto(BwChatMessage.MessageType type, String roomId, Long senderId, String sender, String message) {
        this.type = type;
        this.roomId = roomId;
        this.senderId = senderId;
        this.sender = sender;
        this.message = message;

    }

    @Builder
    public BwChatMessageRequestDto(BwChatMessage.MessageType type, String senderId, String sender, String message) {
        this.type = type;
        this.roomId = roomId;
        this.senderId = null;
        this.sender = sender;
        this.message = message;
    }
}
