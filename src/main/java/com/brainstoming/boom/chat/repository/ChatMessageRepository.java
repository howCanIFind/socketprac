package com.brainstoming.boom.chat.repository;

import com.brainstoming.boom.chat.BwChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<BwChatMessage, Long> {
}
