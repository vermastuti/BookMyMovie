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
@RequestMapping("/api/movie")
@Validated
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Movie movie){
        Movie addedMovie = movieService.add(movie);
        return new ResponseEntity<>(addedMovie, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Movie movie){
        Movie updatedMovie = movieService.update(movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies(){
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{pid}")
    public ResponseEntity<?> getMovieById(@PathVariable("pid") int pid){
        try {
            Movie movieFound = movieService.getById(pid);
            return ResponseEntity.ok(movieFound);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping
//    public ResponseEntity<?> getMovieByTitle(@RequestParam("title") String title){
//        return new ResponseEntity<>(movieService.getByTitle(title), HttpStatus.OK);
//    }

//    @GetMapping
//    public ResponseEntity<?> getMovieByTitleLike(@RequestParam("titleLike") String titleLike){
//        return new ResponseEntity<>(movieService.getByTitleLike(titleLike), HttpStatus.OK);
//    }

}
