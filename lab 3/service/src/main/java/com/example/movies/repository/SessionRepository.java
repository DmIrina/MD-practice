package com.example.movies.repository;

import com.example.movies.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SessionRepository extends JpaRepository<Session, Integer> {

}
