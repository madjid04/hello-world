package com.example.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.entity.Message;
import com.example.helloworld.repository.MessageRepository;

@RestController
@RequestMapping("/hello")
public class MessageController {

	@Value("${spring.datasource.url:NON DEFINI}")
    private String datasourceUrl;

	private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        System.out.println(">>> DATASOURCE URL: " + datasourceUrl);
    }

    // GET — lire tous les messages
    @GetMapping
    public List<Message> getAll() {
        return repository.findAll();
    }

    // POST — créer un message
    @PostMapping
    public Message create(@RequestBody Message message) {
        return repository.save(message);
    }
}
