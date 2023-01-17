package com.example.movies.controller;

import com.example.movies.entity.Movie;
import com.example.movies.entity.Session;
import com.example.movies.service.MovieService;
import com.example.movies.service.SessionService;
import jakarta.validation.Valid;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping(path = "/api/session")
public class SessionController {
    private final AmqpTemplate template;
    private String text;
    private String requestType;
    private String url;
    // private final String uri = "http://localhost:8080/api/session";
    private final String uri = "http://localhost/api/session";

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MovieService movieService;

    public SessionController(AmqpTemplate template) {
        this.template = template;
    }

    @GetMapping()
    public @ResponseBody ArrayList<Session> getAllSessions() {
        text = "all sessions";
        requestType = "GET";
        url = uri;
        template.convertAndSend("session-queue", text + "*" + requestType + "*" + url);
        return sessionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Integer id) {

        Optional<Session> optionalSession = sessionService.findById(id);
        if (optionalSession.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Session session = optionalSession.get();
        text = "session: '" + session.getName() + "', movie: '" + session.getMovieName() + "'";
        requestType = "GET";
        url = uri + "/" + id;
        template.convertAndSend("session-queue", text + "*" + requestType + "*" + url);
        return optionalSession.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @PostMapping()
    public ResponseEntity<Session> addSession(@RequestBody @Valid Session session) throws MalformedURLException {
        Optional<Movie> optionalMovie = movieService.findBySession(session);
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        session.setMovie(optionalMovie.get());

        Session savedSession = sessionService.save(session);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSession.getId()).toUri();

        text = "session: '" + session.getName() + "', movie: '" + session.getMovieName() + "'";
        requestType = "POST";
        url = location.toURL().toString();
        template.convertAndSend("session-queue", text + "*" + requestType + "*" + url);
        return ResponseEntity.created(location).body(savedSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@RequestBody @Valid Session session, @PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieService.findBySession(session);

        // фільм не існує
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        // сеанс вже існує
        Optional<Session> optionalSession = sessionService.findById(id);
        if (optionalSession.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        session.setMovie(optionalMovie.get());
        session.setId(optionalSession.get().getId());
        sessionService.save(session);

        text = "session: '" + session.getName() + "', movie: '" + session.getMovieName() + "'";
        requestType = "PUT";
        url = uri + "/" + id;
        template.convertAndSend("session-queue", text + "*" + requestType + "*" + url);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Session> deleteSession(@PathVariable Integer id) {
        Optional<Session> optionalSession = sessionService.findById(id);
        if (optionalSession.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Session session = optionalSession.get();
        sessionService.deleteById(id);

        text = "session: '" + session.getName() + "', movie: '" + session.getMovieName() + "'";
        requestType = "DELETE";
        url = uri + "/" + id;
        template.convertAndSend("session-queue", text + "*" + requestType + "*" + url);
        return ResponseEntity.noContent().build();  // статус
    }
}