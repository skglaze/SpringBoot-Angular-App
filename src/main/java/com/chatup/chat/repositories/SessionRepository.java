package com.chatup.chat.repositories;

import com.chatup.chat.models.Agent;
import com.chatup.chat.models.Session;
import com.chatup.chat.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository <Session, Long>{
    List<Agent> findActiveSessionsByAgentId(Long agentId, Pageable pageable);
    List<User> findActiveSessionsByUserId(Long sessionId, Pageable pageable);
}
