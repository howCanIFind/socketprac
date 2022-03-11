package com.brainstoming.boom.chat.dto;

import com.brainstoming.boom.chat.BwChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BwRoomResponseDto {

    private Long roomId;

    private Long headCount;

    private Long time;

    public BwRoomResponseDto(Long roomId, Long headCount, Long time) {
        this.roomId = roomId;
        this.headCount = headCount;
        this.time = time;
    }
}
