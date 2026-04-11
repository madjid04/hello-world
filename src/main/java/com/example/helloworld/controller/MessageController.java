package com.example.helloworld.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.HelloworldApplication;
import com.example.helloworld.entity.Message;
import com.example.helloworld.repository.MessageRepository;

@RestController
@RequestMapping("/hello")
public class MessageController {

	private static final Logger log = LoggerFactory.getLogger(HelloworldApplication.class);

	private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    // GET — lire tous les messages
    @GetMapping
    public List<Message> getAll() {
    	log.info("GET /hello — {} messages trouvés", repository.count());
        return repository.findAll();
    }

    // POST — créer un message
    @PostMapping
    public Message create(@RequestBody Message message) {
    	Message saved = repository.save(message);
    	log.info("POST /hello — message créé id={} content={}", saved.getId(), saved.getContent());
        return saved;
    }
}
