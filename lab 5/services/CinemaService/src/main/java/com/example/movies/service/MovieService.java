package com.example.movies.service;

import com.example.movies.entity.Movie;
import com.example.movies.entity.Session;

import java.util.ArrayList;
import java.util.Optional;

public interface MovieService {
    ArrayList<Movie> findAll();

    Optional<Movie> findById(Integer id);

    Optional<Movie> findBySession(Session session);

    Movie save(Movie movie);

    boolean deleteById(Integer id);

    void delete(Movie movie);

    boolean deleteAllData();
}
