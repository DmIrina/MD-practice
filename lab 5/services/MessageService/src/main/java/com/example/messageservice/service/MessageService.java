package com.example.messageservice.service;

import com.example.messageservice.entity.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MessageService {
    ArrayList<Message> findAll();
    Optional<Message> findById(Integer id);
    Optional<Message> findByMessage(Message message);
    List<Message> findAllByType(String type);

    Message save(Message message);

    boolean deleteById(Integer id);
    boolean deleteAllByType(String type);
}
