package com.chatup.chat.repositories;

import com.chatup.chat.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesBySessionId(Long sessionId, Pageable pageable);
}
