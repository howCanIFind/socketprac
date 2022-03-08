package com.project.brainstomingbe.chat.repository;

import com.project.brainstomingbe.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
