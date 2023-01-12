package com.example.ticketservice.repositories;

import com.example.ticketservice.entities.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public Ticket findByIndex(int index) {
        return tickets.get(index);
    }

    @Override
    public void add(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public List<Ticket> getTickets() {
        return tickets;
    }
}
