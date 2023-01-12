package com.example.ticketservice.controllers;

import com.example.ticketservice.dto.TicketDto;
import com.example.ticketservice.entities.Session;
import com.example.ticketservice.entities.Ticket;
import com.example.ticketservice.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/tickets")
@Controller
public class TicketController {
    // private final String cinemaServiceUrl = "http://localhost:8080/api/session";
    private final String cinemaServiceUrl = "http://cinema-service/api/session";
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getById(@PathVariable int id) {
        Ticket ticket = ticketService.findByIndex(id);
        try {
            RestTemplate template = new RestTemplate();
            Session session = template.getForObject(cinemaServiceUrl + "/" + ticket.getSessionId(), Session.class);
            TicketDto dto = new TicketDto(ticket, session);
            return ResponseEntity.ok(dto);
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode().is4xxClientError())
                return ResponseEntity.notFound().build();
            else if (ex.getStatusCode().is5xxServerError())
                return ResponseEntity.internalServerError().build();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTicketDtos() {
        return ResponseEntity.ok(ticketService.getTicketDtos());
    }

    @PostMapping("/create")
    public void createTickets() {
        ticketService.createTickets(5);
    }
}
