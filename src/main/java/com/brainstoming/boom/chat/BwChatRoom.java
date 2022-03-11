package com.brainstoming.boom.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class BwChatRoom {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @Column
    private Long headCount;

    @Column
    private Long time;

    public BwChatRoom(Long headCount, Long time) {
        this.headCount = headCount;
        this.time = time;
    }
}
