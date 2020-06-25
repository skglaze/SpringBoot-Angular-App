package com.chatup.chat.controllers;

import com.chatup.chat.models.Agent;
import com.chatup.chat.repositories.AgentRepository;
import com.chatup.chat.repositories.SessionRepository;
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
@RequestMapping("/api/v1/agents")
public class AgentController {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private SessionRepository sessionRepository;


    @GetMapping
    public List<Agent> list(){
        return agentRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Agent agent){
        agentRepository.save(agent);
    }

    @GetMapping("/{id}")
    public Agent get(@PathVariable("id") long id){
        return agentRepository.getOne(id);
    }

    @GetMapping("/{id}/sessions")
    public List<Agent> getActiveSessionsByAgentId(@PathVariable (value = "id") Long agentId, Pageable pageable){
        return sessionRepository.findActiveSessionsByAgentId(agentId, pageable);
    }

}
