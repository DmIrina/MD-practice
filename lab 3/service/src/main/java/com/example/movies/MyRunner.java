package com.example.movies;

import com.example.movies.entity.Movie;
import com.example.movies.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(150000);
        if (movieRepository.count() < 3) {
            var nm1 = new Movie();
            nm1.setName("Avatar");
            nm1.setDescription("Avatar: 2022");
            movieRepository.save(nm1);
            var nm2 = new Movie();
            nm2.setName("One at Home");
            nm2.setDescription("Old Film");
            movieRepository.save(nm2);
            var nm3 = new Movie();
            nm3.setName("The World War");
            nm3.setDescription("New Film 3 Deskr - 3");
            movieRepository.save(nm3);
        }
    }
}