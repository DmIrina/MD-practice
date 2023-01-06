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
        var res = (ArrayList<Movie>) movieRepository.findAll();
        return res;
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        Optional<Movie> opt = movieRepository.findById(id);
        if (opt.isPresent())
            return opt;
        else
            return Optional.empty();
    }

    @Override
    public Optional<Movie> findBySession(Session session) {
        Optional<Movie> opt = movieRepository.findById(session.getMovie().getId());
        if (opt.isPresent())
            return opt;
        else
            return Optional.empty();
    }

    public boolean deleteById(Integer id) {
        if (!movieRepository.existsById(id)) return false;
        movieRepository.deleteById(id);
        return true;
    }

    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public Movie save(Movie movie) {
        SessionServiceImpl sessionServiceImpl = new SessionServiceImpl();
        if (movie.getId() == null) {                            // POST
            Movie savedMovie = movieRepository.save(movie);
            return savedMovie;
        } else {
            if (movieRepository.existsById(movie.getId())) {    // PUT      -
                // видалити сеанси, які були до цього
//                var sessions = movieRepository.findById(movie.getId()).get().getSessions();
//                sessions.forEach(session -> sessionServiceImpl.deleteById(session.getId()));

                Movie savedMovie = movieRepository.save(movie);
                return savedMovie;
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean deleteAllData() {
        movieRepository.deleteAll();
        if (movieRepository.count() == 0)
            return true;
        else
            return false;
    }
}
