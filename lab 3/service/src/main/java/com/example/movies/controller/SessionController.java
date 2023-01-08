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
import java.util.Optional;

@CrossOrigin
@Controller // This means that this class is a Controller
// @RequestMapping(path="") // спільна початкова частина URL буде підставлена в Mapping
public class SessionController {

    @Autowired
    SessionServiceImpl sessionServiceImpl;

    @Autowired
    MovieServiceImpl movieServiceImpl;


    @Autowired
    public SessionController(SessionServiceImpl sessionServiceImpl, MovieServiceImpl movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
        this.sessionServiceImpl = sessionServiceImpl;
    }


    @GetMapping("/api/session")
    public @ResponseBody ArrayList<Session> getAll() {
        return sessionServiceImpl.findAll();
    }

    // ResponseEntity - дозволяє в респонзі передати код та об"єкт
    @GetMapping("/api/session/{id}")
    public ResponseEntity<Session> getById(@PathVariable Integer id) {
        Optional<Session> optionalSession = sessionServiceImpl.findById(id);
        if (!optionalSession.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalSession.get());
    }


    @DeleteMapping("/api/session/{id}")
    public ResponseEntity<Session> delete(@PathVariable Integer id) {
        Optional<Session> optionalSession = sessionServiceImpl.findById(id);
        if (!optionalSession.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        sessionServiceImpl.delete(optionalSession);
        return ResponseEntity.noContent().build();  // статус
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<?> delete() {
//        if (sessionServiceImpl.deleteAllData())
//            return ResponseEntity.noContent().build();      // ok - 204 (без тексту)
//        else
//            return ResponseEntity.unprocessableEntity().build();
//    }


    @PostMapping("/api/session")
    public ResponseEntity<Session> create(@RequestBody @Valid Session session) {
        Optional<Movie> optionalMovie = movieServiceImpl.findBySession(session);
        if (!optionalMovie.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        session.setMovie(optionalMovie.get());

        Session savedSession = sessionServiceImpl.save(session);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSession.getId()).toUri();

        return ResponseEntity.created(location).body(savedSession);
    }

    @PutMapping("/api/session/{id}")
    public ResponseEntity<Session> update(@RequestBody @Valid Session session, @PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieServiceImpl.findBySession(session);

        // фільм не існує
        if (!optionalMovie.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        // сеанс вже існує
        Optional<Session> optionalSession = sessionServiceImpl.findById(id);
        if (!optionalSession.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        session.setMovie(optionalMovie.get());
        session.setId(optionalSession.get().getId());
        sessionServiceImpl.save(session);

        return ResponseEntity.noContent().build();
    }
}
