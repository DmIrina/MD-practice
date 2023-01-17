package com.example.movieservice.service;

import com.example.movieservice.entities.Movie;

import java.util.ArrayList;
import java.util.Optional;

public interface MovieService {
    ArrayList<Movie> findAll();

    Optional<Movie> findById(Integer id);

    Optional<Movie> findByMovie(Movie session);

    Movie save(Movie movie);

    boolean deleteById(Integer id);

    void delete(Movie movie);

    boolean deleteAllData();
}