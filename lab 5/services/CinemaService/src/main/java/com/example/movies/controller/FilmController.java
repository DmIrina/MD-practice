package com.example.movies.controller;

import com.example.movies.entity.Movie;
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
@Controller
@RequestMapping(path = "/api/film")
public class FilmController {

    @Autowired
    MovieServiceImpl movieServiceImpl;

    @Autowired
    SessionServiceImpl sessionServiceImpl;


    @GetMapping()
    public @ResponseBody
    ArrayList<Movie> getAll() {
        return movieServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieServiceImpl.findById(id);
        return optionalMovie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> delete(@PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieServiceImpl.findById(id);
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        movieServiceImpl.delete(optionalMovie.get());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete() {
        if (movieServiceImpl.deleteAllData())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.unprocessableEntity().build();
    }


    @PostMapping
    public ResponseEntity<Movie> create(@Valid @RequestBody Movie movie) {
        Movie savedMovie = movieServiceImpl.save(movie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMovie.getId()).toUri();
        return ResponseEntity.created(location).body(savedMovie);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable Integer id, @Valid @RequestBody Movie movie) {
        Optional<Movie> optionalMovie = movieServiceImpl.findById(id);

        // якщо фільм не існує
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        movie.setId(optionalMovie.get().getId());
        movieServiceImpl.save(movie);
        return ResponseEntity.noContent().build();
    }
}