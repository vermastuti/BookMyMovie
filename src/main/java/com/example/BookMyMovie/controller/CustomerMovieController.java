package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/movies")
@Validated
public class CustomerMovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getAllMovies() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{pid}")
    public ResponseEntity<?> getMovieById(@PathVariable("pid") int pid) {
        try {
            Movie movieFound = movieService.getById(pid);
            return ResponseEntity.ok(movieFound);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{pid}/shows")
    public ResponseEntity<?> getMovieShows(@PathVariable Integer pid) {
        return new ResponseEntity<>(movieService.getShowsByMovie(), HttpStatus.OK);
    }

    @GetMapping("/search?title={title}")
    public ResponseEntity<?> getMovieByTitle(@RequestParam("title") String title) {
        return new ResponseEntity<>(movieService.getByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/search?genre={genre}")
    public ResponseEntity<?> getMovieByGenre(@RequestParam("genre") String genre) {
        return new ResponseEntity<>(movieService.getByGenre(genre), HttpStatus.OK);
    }


}
