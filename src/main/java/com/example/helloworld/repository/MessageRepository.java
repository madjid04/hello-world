package com.example.helloworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helloworld.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
