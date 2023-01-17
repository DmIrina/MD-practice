package com.example.messageservice.controller;

import com.example.messageservice.entity.Message;
import com.example.messageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessageController {


    @Autowired
    private MessageService messageService;

    @GetMapping()
    public ResponseEntity<List<Message>> findAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findByMessageById(@PathVariable int id) {
        return messageService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @PostMapping()
    public ResponseEntity<Message> addMessage(@RequestBody @Valid Message message) {
        Optional<Message> optionalMessage = messageService.findByMessage(message);
        if (optionalMessage.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Message savedMessage = messageService.save(message);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMessage.getId()).toUri();

        return ResponseEntity.created(location).body(savedMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@RequestBody @Valid Message message, @PathVariable Integer id) {
        Optional<Message> optionalMessage = messageService.findById(id);

        if (optionalMessage.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        messageService.save(message);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Integer id) {
        Optional<Message> optionalMessage = messageService.findById(id);
        if (optionalMessage.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        messageService.deleteById(optionalMessage.get().getId());
        return ResponseEntity.noContent().build();  // статус
    }

}