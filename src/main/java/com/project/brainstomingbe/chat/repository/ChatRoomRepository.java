package com.project.brainstomingbe.chat.repository;

import com.project.brainstomingbe.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
