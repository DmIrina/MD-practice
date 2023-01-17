package com.example.messageservice.service;

import com.example.messageservice.entity.Message;
import com.example.messageservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Override
    public ArrayList<Message> findAll() {
        return new ArrayList<>(messageRepository.findAll());
    }

    @Override
    public Optional<Message> findById(Integer id) {
        return messageRepository.findById(id);
    }

    @Override
    public Optional<Message> findByMessage(Message message) {
        return messageRepository.findById(message.getId());
    }

    @Override
    public List<Message> findAllByType(String type) {
        return messageRepository.findAll().stream().filter(m -> Objects.equals(m.getRequestType(), type)).toList();
    }

    @Override
    public Message save(Message message) {
        if (message.getId() == null) {                            // POST
            return messageRepository.save(message);
        } else {
            if (messageRepository.existsById(message.getId())) {    // PUT      -
                return messageRepository.save(message);
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!messageRepository.existsById(id)) {
            return false;
        }
        messageRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAllByType(String type) {
        List<Message> messages = findAllByType(type);

        if (messages.isEmpty()) {
            return false;
        }
        messageRepository.deleteAll(messages);
        return true;
    }
}
