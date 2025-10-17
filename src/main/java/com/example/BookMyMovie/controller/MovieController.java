package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.service.BookingService;
import com.example.BookMyMovie.service.MovieService;
import com.example.BookMyMovie.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;


@RestController
@RequestMapping("/api/movies")
@Validated
@CrossOrigin
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    ShowService showService;

    @Autowired
    BookingService bookingService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllMovies() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{pid}")
    public ResponseEntity<?> getMovieById(@PathVariable("pid") int pid) {
        try {
            Movie movieFound = movieService.getById(pid);
            return ResponseEntity.ok(movieFound);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search?title={title}")
    public ResponseEntity<?> getMovieByTitle(@RequestParam("title") String title) {
        return new ResponseEntity<>(movieService.getByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/search?genre={genre}")
    public ResponseEntity<?> getMovieByGenre(@RequestParam("genre") String genre) {
        return new ResponseEntity<>(movieService.getByGenre(genre), HttpStatus.OK);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<?> add(@Valid @RequestBody Movie movie) {
        try {
            Movie addedMovie = movieService.add(movie);
            return new ResponseEntity<>(addedMovie, HttpStatus.OK);
        } catch (DuplicateIdFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/admin/update")
    public ResponseEntity<?> update(@Valid @RequestBody Movie movie) {
        try {
            Movie updatedMovie = movieService.update(movie);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/admin/cancel/{movieId}")
    public ResponseEntity<?> cancel(@PathVariable("movieId") int movieId){
        try {
            movieService.cancelMovie(movieId);
//            showService.cancelShowsByMovie(movieId);
//            bookingService.cancelBookingsByMovieId(movieId);
            return new ResponseEntity<>("Movie cancelled", HttpStatus.OK);
        } catch (IdDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
