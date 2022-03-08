package com.project.brainstomingbe.chat.service;

import com.project.brainstomingbe.User.User;
import com.project.brainstomingbe.User.UserRepository;
import com.project.brainstomingbe.User.UserService;
import com.project.brainstomingbe.chat.ChatRoom;
import com.project.brainstomingbe.chat.ChatRoomRequestDto;
import com.project.brainstomingbe.chat.ChatRoomResponseDto;
import com.project.brainstomingbe.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    //레디스 저장소 사용
    //key hashKey value 구조
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOpsEnterInfo;

    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    private final UserRepository userRepository;
    // 채팅룸에 입장한 클라이언트의 sessionId 와 채팅룸 id 를 맵핑한 정보 저장
    public static final String ENTER_INFO = "ENTER_INFO";

    // 채팅방 생성
    public ChatRoomResponseDto createChatRoom(ChatRoomRequestDto requestDto) {
        User user = userService.findById(requestDto.getUserId());
        ChatRoom chatRoom = new ChatRoom(requestDto.getCategory(), user);
        chatRoomRepository.save(chatRoom);
        ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom, user);
        return chatRoomResponseDto;
    }

    // 유저가 입장한 채팅방 ID 와 유저 세션 ID 맵핑 정보 저장
    //Enter라는 곳에 sessionId와 roomId를 맵핑시켜놓음
    public void setUserEnterInfo(String sessionId, String roomId) {
        hashOpsEnterInfo.put(ENTER_INFO, sessionId, roomId);
    }

    // 개별 채팅방 조회
    public ChatRoom getEachChatRoom(Long id) {
        return chatRoomRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 채팅방이 존재하지 않습니다.")
        );
    }

    // 유저 세션으로 입장해 있는 채팅방 ID 조회
    public String getUserEnterRoomId(String sessionId) {

        return hashOpsEnterInfo.get(ENTER_INFO, sessionId);
    }

    // 유저 세션정보와 맵핑된 채팅방 ID 삭제
    //한 유저는 하나의 룸 아이디에만 맵핑
    // 실시간으로 보는 방은 하나이기 떄문이다.
    public void removeUserEnterInfo(String sessionId) {
        hashOpsEnterInfo.delete(ENTER_INFO, sessionId);
    }

    // test 용
    public ChatRoom getRoom(Long id) {
        Optional<ChatRoom> chatRoom =  chatRoomRepository.findById(id);
        return chatRoom.orElseThrow(NullPointerException::new);
    }
}
