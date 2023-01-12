package com.example.ticketservice.services;

import com.example.ticketservice.dto.TicketDto;
import com.example.ticketservice.entities.Session;
import com.example.ticketservice.entities.Ticket;
import com.example.ticketservice.repositories.TicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final int ROW_MAX = 15;
    private final int PLACE_MAX = 20;

    private final String cinemaServiceUrl = "http://cinema-service/api/session";
    // private final String cinemaServiceUrl = "http://localhost:8080/api/session";

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket findByIndex(int index) {
        return ticketRepository.findByIndex(index);
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.getTickets();
    }

    @Override
    public void createTickets(int count) {
        List<Session> sessions = getSessions();

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Session session = sessions.get(random.nextInt(sessions.size() - 1));
            int row = random.nextInt(ROW_MAX);
            int place = random.nextInt(PLACE_MAX);

            Ticket ticket = new Ticket();
            ticket.setSessionId(session.getId());
            ticket.setRow(row);
            ticket.setPlace(place);
            ticketRepository.add(ticket);
        }
    }

    @Override
    public List<TicketDto> getTicketDtos() {
        List<Session> sessions = getSessions();
        List<TicketDto> tickets = getTickets().stream().map(t ->
                new TicketDto(t, sessions.stream()
                        .filter(s -> s.getId() == t.getSessionId())
                        .findFirst().get())).toList();
        return tickets;
    }

    public List<Session> getSessions() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(cinemaServiceUrl, Object[].class);
        Object[] objects = responseEntity.getBody();

        if (objects == null) {
            throw new NoSuchElementException();
        }

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, Session.class))
                .collect(Collectors.toList());
    }
}
