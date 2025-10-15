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
@RequestMapping("/api/movies")
@Validated
public class MovieController {

    @Autowired
    MovieService movieService;

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
    public ResponseEntity<?> add( @RequestBody Movie movie) {
        System.out.println("----------------------------here-----   " + movie.toString());
        Movie addedMovie = movieService.add(movie);
        return new ResponseEntity<>(addedMovie, HttpStatus.OK);
    }

    @PutMapping("/admin/update")
    public ResponseEntity<?> update(@RequestBody Movie movie) {
        Movie updatedMovie = movieService.update(movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/admin/cancel/{pid}")
    public ResponseEntity<?> cancel(@RequestParam("pid") int pid){
        try {
            Movie movieFound = movieService.getById(pid);
            return ResponseEntity.ok(movieService.delete(pid));
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
