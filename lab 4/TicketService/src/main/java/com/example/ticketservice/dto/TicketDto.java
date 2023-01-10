package com.example.ticketservice.dto;

import com.example.ticketservice.entities.Session;
import com.example.ticketservice.entities.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
    private Session session;
    private int row;
    private int place;

    public TicketDto(Ticket ticket, Session session) {
        this.session = session;
        this.row = ticket.getRow();
        this.place = ticket.getPlace();
    }
}
