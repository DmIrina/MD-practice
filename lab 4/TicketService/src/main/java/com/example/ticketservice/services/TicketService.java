package com.example.ticketservice.services;

import com.example.ticketservice.dto.TicketDto;
import com.example.ticketservice.entities.Ticket;

import java.util.List;

public interface TicketService {
    Ticket findByIndex(int index);

    List<Ticket> getTickets();

    void createTickets(int count);

    List<TicketDto> getTicketDtos();
}
