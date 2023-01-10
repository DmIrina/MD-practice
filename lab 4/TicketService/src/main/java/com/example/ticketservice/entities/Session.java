package com.example.ticketservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Session {
    private int id;
    private String name;
    private String time;
    private String date;
    private String room;
    private String movieName;
    private int movieId;
}

