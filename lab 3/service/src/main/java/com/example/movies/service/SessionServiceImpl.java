package com.example.movies.service;

import com.example.movies.entity.Session;
import com.example.movies.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public ArrayList<Session> findAll() {
        return new ArrayList<Session> (sessionRepository.findAll());
    }

    @Override
    public Optional<Session> findById(Integer id) {
        Optional<Session> opt = sessionRepository.findById(id);
        if (opt.isPresent())
            return opt;
        else
            return null;
    }

    @Override
    public Session save(Session session) {                    // PUT , POST
        if (session.getId() == null) {
            Session savedMovie = sessionRepository.save(session);
            return savedMovie;
        } else {
            if (sessionRepository.existsById(session.getId())) {
                Session savedMovie = sessionRepository.save(session);
                return savedMovie;
            } else {
                return null;
            }
        }
    }


    public boolean deleteById(Integer id) {
        if (!sessionRepository.existsById(id)) return false;
        sessionRepository.deleteById(id);
        return true;
    }

    @Override
    public void delete(Optional<Session> optionalSession) {
        sessionRepository.delete(optionalSession.get());
    }



}
