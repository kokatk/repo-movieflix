package com.movieflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.model.Category;
import com.movieflix.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long >{
    List<Movie> findMovieByCategories(List<Category> categories);
}
