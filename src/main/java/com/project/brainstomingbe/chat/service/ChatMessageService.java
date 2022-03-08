package com.project.brainstomingbe.chat.service;

import com.project.brainstomingbe.User.User;
import com.project.brainstomingbe.User.UserService;
import com.project.brainstomingbe.chat.ChatMessage;
import com.project.brainstomingbe.chat.ChatMessageRequestDto;
import com.project.brainstomingbe.chat.ChatMessageResponseDto;
import com.project.brainstomingbe.chat.ChatRoom;
import com.project.brainstomingbe.chat.repository.ChatMessageRepository;
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
    public void sendChatMessage(ChatMessageRequestDto chatMessageResponseDto) {
        if (ChatMessage.MessageType.ENTER.equals(chatMessageResponseDto.getType())) {

            chatMessageResponseDto.setSender("[알림]");
            chatMessageResponseDto.setMessage(chatMessageResponseDto.getSender() + "님이 방에 입장했습니다.");

            log.info("ENTER 데이터 ");
        } else if (ChatMessage.MessageType.QUIT.equals(chatMessageResponseDto.getType())) {
            chatMessageResponseDto.setSender("[알림]");
            chatMessageResponseDto.setMessage(chatMessageResponseDto.getSender() + "님이 방에서 나갔습니다.");
            log.info("QUIT 데이터 ");
        }
        //topic은 chatroom이다
        //conver
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessageResponseDto);
    }

    public void sendChatMessage(ChatMessageResponseDto chatMessageResponseDto) {
        if (ChatMessage.MessageType.ENTER.equals(chatMessageResponseDto.getType())) {

            chatMessageResponseDto.setSender("[알림]");
            chatMessageResponseDto.setMessage(chatMessageResponseDto.getSender() + "님이 방에 입장했습니다.");

            log.info("ENTER 데이터 ");
        } else if (ChatMessage.MessageType.QUIT.equals(chatMessageResponseDto.getType())) {
            chatMessageResponseDto.setSender("[알림]");
            chatMessageResponseDto.setMessage(chatMessageResponseDto.getSender() + "님이 방에서 나갔습니다.");
            log.info("QUIT 데이터 ");
        }
        //topic은 chatroom이다
        //conver
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessageResponseDto);
    }

    public void save(ChatMessageResponseDto chatMessageResponseDto) {
        User user = userService.findById(chatMessageResponseDto.getUserId());
        ChatRoom room = chatRoomService.getEachChatRoom(Long.parseLong(chatMessageResponseDto.getRoomId()));
        ChatMessage chatmessage = new ChatMessage();

        chatmessage.setType(chatMessageResponseDto.getType());
        chatmessage.setMessage(chatMessageResponseDto.getMessage());
        chatmessage.setUser(user);
        chatmessage.setRoom(room);

        chatMessageRepository.save(chatmessage);
    }
}
