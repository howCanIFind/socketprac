package com.brainstoming.boom.chat.service;

import com.brainstoming.boom.chat.BwChatMessage;
import com.brainstoming.boom.chat.BwChatRoom;
import com.brainstoming.boom.chat.dto.BwChatMessageRequestDto;
import com.brainstoming.boom.chat.dto.BwChatMessageResponseDto;
import com.brainstoming.boom.chat.repository.ChatMessageRepository;
import com.brainstoming.boom.user.User;
import com.brainstoming.boom.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    // destination 정보에서 roomId 추출
    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

    // 채팅방에 메시지 발송
    public void sendChatMessage(BwChatMessageResponseDto bwChatMessageResponseDto) {
        if (BwChatMessage.MessageType.ENTER.equals(bwChatMessageResponseDto.getType())) {

            bwChatMessageResponseDto.setSender("[알림]");
            bwChatMessageResponseDto.setMessage(bwChatMessageResponseDto.getSender() + "님이 방에 입장했습니다.");

            log.info("ENTER 데이터 ");
        } else if (BwChatMessage.MessageType.QUIT.equals(bwChatMessageResponseDto.getType())) {
            bwChatMessageResponseDto.setSender("[알림]");
            bwChatMessageResponseDto.setMessage(bwChatMessageResponseDto.getSender() + "님이 방에서 나갔습니다.");
            log.info("QUIT 데이터 ");
        }
        //topic은 chatroom이다
        //conver
        redisTemplate.convertAndSend(channelTopic.getTopic(), bwChatMessageResponseDto);
    }

    public void save(BwChatMessageResponseDto bwChatMessageResponseDto) {
        User user = userService.findById(bwChatMessageResponseDto.getUserId());
        BwChatRoom room = chatRoomService.getEachChatRoom(Long.parseLong(bwChatMessageResponseDto.getRoomId()));
        BwChatMessage chatmessage = new BwChatMessage();

        chatmessage.setType(bwChatMessageResponseDto.getType());
        chatmessage.setMessage(bwChatMessageResponseDto.getMessage());
        chatmessage.setUser(user);
        chatmessage.setRoom(room);
        chatmessage.setCreatedAt(bwChatMessageResponseDto.getCreatedAt());

        chatMessageRepository.save(chatmessage);
    }

    public void enternickname(BwChatMessageRequestDto bwChatMessageRequestDto) {
        String sender = bwChatMessageRequestDto.getSender();
        log.info(sender);


    }
}
