package com.example.movies.controller;

import com.example.movies.entity.Movie;
import com.example.movies.entity.Session;
import com.example.movies.service.MovieServiceImpl;
import com.example.movies.service.SessionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping(path = "/api/session")
public class SessionController {
    private String status = "ok";

    @Autowired
    SessionServiceImpl sessionServiceImpl;

    @Autowired
    MovieServiceImpl movieServiceImpl;


    @Autowired
    public SessionController(SessionServiceImpl sessionServiceImpl, MovieServiceImpl movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
        this.sessionServiceImpl = sessionServiceImpl;
    }


    @GetMapping()
    public @ResponseBody
    ArrayList<Session> getAll() {
        return sessionServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getById(@PathVariable Integer id) throws InterruptedException {
        if (!Objects.equals(status, "ok")) {
            Thread.sleep(10 * 1000);
        }
        Optional<Session> optionalSession = sessionServiceImpl.findById(id);
        return optionalSession.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Session> delete(@PathVariable Integer id) {
        Optional<Session> optionalSession = sessionServiceImpl.findById(id);
        if (optionalSession.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        sessionServiceImpl.delete(optionalSession);
        return ResponseEntity.noContent().build();  // статус
    }


    @PostMapping()
    public ResponseEntity<Session> create(@RequestBody @Valid Session session) {
        Optional<Movie> optionalMovie = movieServiceImpl.findBySession(session);
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        session.setMovie(optionalMovie.get());

        Session savedSession = sessionServiceImpl.save(session);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSession.getId()).toUri();

        return ResponseEntity.created(location).body(savedSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> update(@RequestBody @Valid Session session, @PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieServiceImpl.findBySession(session);

        // фільм не існує
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        // сеанс вже існує
        Optional<Session> optionalSession = sessionServiceImpl.findById(id);
        if (optionalSession.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        session.setMovie(optionalMovie.get());
        session.setId(optionalSession.get().getId());
        sessionServiceImpl.save(session);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/session/bad")
    public void simulateBadRequest() {
        status = "failed";
    }
}
