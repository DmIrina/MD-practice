package com.example.movies.service;

import com.example.movies.entity.Movie;
import com.example.movies.entity.Session;
import com.example.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service                                                // provide some business functionalities.
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public ArrayList<Movie> findAll() {
        return new ArrayList<>( movieRepository.findAll());
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        return movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> findBySession(Session session) {
        return movieRepository.findById(session.getMovie().getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!movieRepository.existsById(id)) return false;
        movieRepository.deleteById(id);
        return true;
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public Movie save(Movie movie) {
        if (movie.getId() == null) {                            // POST
            return movieRepository.save(movie);
        } else {
            if (movieRepository.existsById(movie.getId())) {    // PUT      -
                return movieRepository.save(movie);
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean deleteAllData() {
        movieRepository.deleteAll();
        return movieRepository.count() == 0;
    }
}
