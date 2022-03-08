package com.project.brainstomingbe.chat;

import com.project.brainstomingbe.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomResponseDto {

    private Long roomId;

    private String category;

    private User user;

    public ChatRoomResponseDto(ChatRoom chatRoom, User user) {
        this.roomId = chatRoom.getId();
        this.category = chatRoom.getCategory();
        this.user = user;
    }
}
