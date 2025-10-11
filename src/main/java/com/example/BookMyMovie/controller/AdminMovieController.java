package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies")
@Validated
public class AdminMovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Movie movie) {
        Movie addedMovie = movieService.add(movie);
        return new ResponseEntity<>(addedMovie, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Movie movie) {
        Movie updatedMovie = movieService.update(movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

}
