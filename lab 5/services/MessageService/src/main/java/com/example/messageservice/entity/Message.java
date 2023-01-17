package com.example.messageservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2048)
    private String text;

    private String requestType;

    @Column(length = 2048)
    private String url;

    private String dateTime;

    public Message(String text, String requestType, String url, String dateTime) {
        this.text = text;
        this.requestType = requestType;
        this.url = url;
        this.dateTime = dateTime;
    }


//    public Message(String text, LocalDateTime time) {
//        this.text = text;
//        this.time = time;
//    }
}