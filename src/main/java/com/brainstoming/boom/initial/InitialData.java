package com.brainstoming.boom.initial;

import com.brainstoming.boom.chat.BwChatRoom;
import com.brainstoming.boom.chat.repository.ChatRoomRepository;
import com.brainstoming.boom.user.User;
import com.brainstoming.boom.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitialData implements ApplicationRunner {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
//        String password = bCryptPasswordEncoder.encode("123456");
//        User user = new User("123@gmail.com", "익명의 프로그래머",password );
        BwChatRoom chatRoom = new BwChatRoom(2L, 1L);

        User user = new User("test");

        userRepository.save(user);

        chatRoomRepository.save(chatRoom);
    }
}
