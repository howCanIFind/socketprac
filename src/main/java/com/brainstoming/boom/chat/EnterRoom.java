package com.brainstoming.boom.chat;

import com.brainstoming.boom.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class EnterRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_room")
    private BwChatRoom room;

    public EnterRoom(User user, BwChatRoom room) {
        this.user = user;
        this.room = room;
    }
}
