package com.example.messageservice.controller;


import com.example.messageservice.entity.Message;
import com.example.messageservice.service.MessageService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableRabbit
@Component
public class RabbitHandler {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private MessageService messageService;

    @RabbitListener(queues = {"session-queue", "movie-queue"})
    // @RabbitListener(queues = {"session-queue"})
    public void handle(String text) {
        String[] values = text.split("\\*");

        messageService.save(new Message(values[0], values[1], values[2], LocalDateTime.now().format(formatter)));
    }
}
