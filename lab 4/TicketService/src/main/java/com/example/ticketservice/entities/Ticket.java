package com.example.ticketservice.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {
    private int sessionId;
    private int row;
    private int place;
}
