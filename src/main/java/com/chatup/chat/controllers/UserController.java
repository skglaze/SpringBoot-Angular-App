package com.chatup.chat.controllers;

import com.chatup.chat.models.User;
import com.chatup.chat.repositories.SessionRepository;
import com.chatup.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<User> list(){
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping("/{id}/sessions")
    public List<User> getActiveSessionsByUserId(@PathVariable (value = "id") Long userId, Pageable pageable){
        return sessionRepository.findActiveSessionsByUserId(userId, pageable);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") long id){
        return userRepository.getOne(id);
    }
}
