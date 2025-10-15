package com.example.BookMyMovie.controller;


import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.exception.ShowIdAlreadyExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.MovieShow;
import com.example.BookMyMovie.service.MovieService;
import com.example.BookMyMovie.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
//@Validated
public class ShowController {

    @Autowired
    ShowService showService;

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<?> addShow(@RequestBody MovieShow movieShow) {
        try {
            MovieShow movieShowResult = showService.add(movieShow);
            return new ResponseEntity<>(movieShowResult, HttpStatus.CREATED);
        } catch (ShowIdAlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/all")
    public  ResponseEntity<?> viewallshow() {
        List<MovieShow> movieShows = showService.viewAllShow();
        return new ResponseEntity<>(movieShows, HttpStatus.OK);
    }

    @GetMapping("/viewall/{mid}")
    public  ResponseEntity<?> viewByMovieId(@PathVariable("mid") int mid) {
        try {
            Movie movie = movieService.getById(mid);
            List<MovieShow> movieShows = showService.findByMovie(movie);
            return new ResponseEntity<>(movieShows, HttpStatus.OK);
        } catch (IdDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
