package com.brainstoming.boom.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("찾는 유저가 없습니다")
        );
    }

    public Long register(UserNickRequestDto requestDto) {

        User user = new User(requestDto.getNickname());
        userRepository.save(user);
        return user.getId();
    }
}
