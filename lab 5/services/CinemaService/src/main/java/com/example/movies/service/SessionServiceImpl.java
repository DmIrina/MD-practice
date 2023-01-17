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
        return new ArrayList<>(sessionRepository.findAll());
    }

    @Override
    public Optional<Session> findById(Integer id) {
        return sessionRepository.findById(id);
    }

    @Override
    public Session save(Session session) {                    // PUT , POST
        if (session.getId() == null) {
            return sessionRepository.save(session);
        } else {
            if (sessionRepository.existsById(session.getId())) {
                return sessionRepository.save(session);
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!sessionRepository.existsById(id)) {
            return false;
        }
        sessionRepository.deleteById(id);
        return true;
    }

    @Override
    public void delete(Session session) {
        sessionRepository.delete(session);
    }
}