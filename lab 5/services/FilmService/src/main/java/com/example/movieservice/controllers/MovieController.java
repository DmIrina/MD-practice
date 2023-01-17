package com.example.movieservice.controllers;


import com.example.movieservice.entities.Movie;
import com.example.movieservice.service.MovieServiceImpl;
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
@RequestMapping(path = "/api/movie")
public class MovieController {
    private final AmqpTemplate template;
    private String text;
    private String requestType;
    private String url;
    // private final String uri = "http://localhost:8082/api/movie";
    private final String uri = "http://localhost/api/movie";

    @Autowired
    MovieServiceImpl movieServiceImpl;

    public MovieController(AmqpTemplate template) {
        this.template = template;
    }

    @GetMapping()
    public @ResponseBody
    ArrayList<Movie> getAll() {
        text = "all movies";
        requestType = "GET";
        url = uri;
        template.convertAndSend("movie-queue", text + "*" + requestType + "*" + url);
        return movieServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieServiceImpl.findById(id);

        if (optionalMovie.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Movie movie = optionalMovie.get();
        text = "movie: '" + movie.getName() + "'";
        requestType = "GET";
        url = uri + "/" + id;
        template.convertAndSend("movie-queue", text + "*" + requestType + "*" + url);
        return optionalMovie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) throws MalformedURLException {
        Movie savedMovie = movieServiceImpl.save(movie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMovie.getId()).toUri();

        text = "movie: '" + movie.getName() + "'";
        requestType = "POST";
        url = location.toURL().toString();
        template.convertAndSend("movie-queue", text + "*" + requestType + "*" + url);
        return ResponseEntity.created(location).body(savedMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @Valid @RequestBody Movie movie) {
        Optional<Movie> optionalMovie = movieServiceImpl.findById(id);

        // якщо фільм не існує
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        movie.setId(optionalMovie.get().getId());
        movieServiceImpl.save(movie);

        text = "movie: '" + movie.getName() + "'";
        requestType = "PUT";
        url = uri + "/" + id;
        template.convertAndSend("movie-queue", text + "*" + requestType + "*" + url);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Integer id) {
        Optional<Movie> optionalMovie = movieServiceImpl.findById(id);
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        movieServiceImpl.deleteById(id);

        text = "movie: '" + optionalMovie.get().getName() + "'";
        requestType = "DELETE";
        url = uri + "/" + id;
        template.convertAndSend("movie-queue", text + "*" + requestType + "*" + url);
        return ResponseEntity.noContent().build();
    }
}