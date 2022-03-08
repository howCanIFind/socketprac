package com.project.brainstomingbe.chat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatRoomRequestDto {

    private String category;

    private Long userId;
}
