package com.project.brainstomingbe.initialData;

import com.project.brainstomingbe.User.UserRepository;
import com.project.brainstomingbe.chat.ChatRoom;
import com.project.brainstomingbe.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitialData implements ApplicationRunner {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
//        String password = bCryptPasswordEncoder.encode("123456");
//        User user = new User("123@gmail.com", "익명의 프로그래머",password );
        ChatRoom chatRoom = new ChatRoom("brainWriting");

        chatRoomRepository.save(chatRoom);
    }
}
