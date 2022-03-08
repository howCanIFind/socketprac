package com.project.brainstomingbe.User;

import lombok.*;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private Role role; // ROLE_USER, ROLE_ADMIN

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = Role.USER;
    }

}
