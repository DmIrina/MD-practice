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

@Controller // This means that this class is a Controller
@RequestMapping(path="/api/movie") // спільна початкова частина URL буде підставлена в Mapping
public class MovieController {

    @Autowired
    MovieServiceImpl movieServiceImpl;

    @Autowired
    SessionServiceImpl sessionServiceImpl;


    @GetMapping()
    public @ResponseBody ArrayList<Movie> getAll() {
        return movieServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieServiceImpl.findById(id);
        if (!optionalMovie.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalMovie.get());  // статус 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> delete(@PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieServiceImpl.findById(id);
        if (!optionalMovie.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        movieServiceImpl.delete(optionalMovie.get());
        return ResponseEntity.noContent().build();              // 204 - no content
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete() {
        if (movieServiceImpl.deleteAllData())
            return ResponseEntity.noContent().build();      // ok - 204 (без тексту)
        else
            return ResponseEntity.unprocessableEntity().build();
    }

    // localhost:8080/movie  + JSON in BODY
    @PostMapping
    public ResponseEntity<Movie> create(@Valid @RequestBody Movie movie) {
        Movie savedMovie = movieServiceImpl.save(movie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMovie.getId()).toUri();            // буде сформовано LOCATION з URL типу такого: http://localhost:8080/movie/3
        return ResponseEntity.created(location).body(savedMovie);       // 201 - created
    }

    // змінити запис (id не змінювати в жодному разі!!!), дані передаються в body
    // localhost:8080/movie/3522  + JSON in BODY
    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable Integer id, @Valid @RequestBody Movie movie) {
        Optional<Movie> optionalMovie = movieServiceImpl.findById(id);

        // якщо фільм не існує
        if (!optionalMovie.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        movie.setId(optionalMovie.get().getId());
        movieServiceImpl.save(movie);
        return ResponseEntity.noContent().build();                      // ok - 204 (без тексту)
    }
}