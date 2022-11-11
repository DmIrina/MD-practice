package com.example.moviesservice;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class MovieController {
    private MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/good-movies")
    public Collection<Movie> goodMovies() {
        return repository.findAll().stream()
                .filter(this::isGreat)
                .collect(Collectors.toList());
    }

    private boolean isGreat(Movie Movie) {
        return !Movie.getName().equals("Alien") &&
                !Movie.getName().equals("Hunger games") &&
                !Movie.getName().equals("Twilight");
    }
}