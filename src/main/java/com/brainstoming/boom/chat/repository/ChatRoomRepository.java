package com.brainstoming.boom.chat.repository;

import com.brainstoming.boom.chat.BwChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<BwChatRoom, Long> {
}
