package com.example.ticketservice.repositories;

import com.example.ticketservice.dto.TicketDto;
import com.example.ticketservice.entities.Ticket;

import java.util.List;

public interface TicketRepository {
    Ticket findByIndex(int index);

    void add(Ticket ticket);

    List<Ticket> getTickets();
}
