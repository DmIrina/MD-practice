package com.example.movies.repository;

import com.example.movies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// DAO (Data access object) layer:
// @Repository - marker for any class repository (DAO).
// JpaRepository contains the full API of CrudRepository and PagingAndSortingRepository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
  //  Movie findByName(String name);
}
