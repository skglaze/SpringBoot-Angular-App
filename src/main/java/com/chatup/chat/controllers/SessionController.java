package com.chatup.chat.controllers;

import com.chatup.chat.models.Message;
import com.chatup.chat.models.Session;
import com.chatup.chat.repositories.MessageRepository;
import com.chatup.chat.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Session session){
        sessionRepository.save(session);
    }

    @GetMapping("/{id}/messages")
    public List<Message> getAllMessagesBySessionId(@PathVariable (value = "id") Long sessionId, Pageable pageable) {
        return messageRepository.findMessagesBySessionId(sessionId, pageable);
    }

    @GetMapping("/{id}")
    public Session get(@PathVariable("id") long id){
        return sessionRepository.getOne(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody Session updateSession(@PathVariable("id") long id,
                                               @RequestBody String status) {
        Session session = sessionRepository.getOne(id);
        session.setStatus(status);
        return session;
    }
}
