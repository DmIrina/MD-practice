package com.example.moviesservice;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class MovieCommandLineRunner implements CommandLineRunner {

    private final MovieRepository repository;

    public MovieCommandLineRunner(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Stream.of("La Casa de Papel", "Hunger games", "Winx: saga", "Twilight",
                "Titanik", "Passengers", "Alien").forEach(name ->
                repository.save(new Movie(name))
        );
        repository.findAll().forEach(System.out::println);
    }
}