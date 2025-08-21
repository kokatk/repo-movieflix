package com.movieflix.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.DTOrequest.MovieRequest;
import com.movieflix.DTOresponse.MovieResponse;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.model.Movie;
import com.movieflix.service.MovieService;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request){
        Movie savedMovie = movieService.save(MovieMapper.toMovie(request));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        return ResponseEntity.ok(movieService.findAll()
        .stream()
        .map(movie -> MovieMapper.toMovieResponse(movie))
        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return movieService.findById(id)
        .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest request){
        return movieService.update(id, MovieMapper.toMovie(request))
        .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
        .orElse(ResponseEntity.notFound().build());
    }
}

