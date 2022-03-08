package com.project.brainstomingbe.chat;

import com.project.brainstomingbe.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @Column
    private String category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<User> userList = new ArrayList<>();

    public ChatRoom(String category, User user) {
        this.category = category;
        this.userList.add(user);
    }

    public ChatRoom(String category) {
        this.category = category;
        this.userList.add(null);
    }
}
