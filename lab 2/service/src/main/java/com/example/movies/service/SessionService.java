package com.example.movies.service;


import com.example.movies.entity.Session;

import java.util.ArrayList;
import java.util.Optional;

public interface SessionService {
    ArrayList<Session> findAll();

    Optional<Session> findById(Integer id);

    Session save(Session session);

    void delete(Optional<Session> optionalSession);
}
