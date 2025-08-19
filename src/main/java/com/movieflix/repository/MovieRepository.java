package com.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long >{

}
