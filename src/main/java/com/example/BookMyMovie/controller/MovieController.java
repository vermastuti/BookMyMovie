package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.service.BookingService;
import com.example.BookMyMovie.service.MovieService;
import com.example.BookMyMovie.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;


@RestController
@RequestMapping("/api/movies")
@Validated
@CrossOrigin(
        exposedHeaders = "Content-Range"
)
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    ShowService showService;

    @Autowired
    BookingService bookingService;


    @GetMapping("")
    public ResponseEntity<?> getAllMovies() {
        HttpHeaders responseHeaders = new HttpHeaders();
        // Format the header as expected by the React data provider
        // E.g., Content-Range: items 0-24/319
        responseHeaders.add("Content-Range", "items 0-14/14");

        return new ResponseEntity<>(movieService.getAll(), responseHeaders, HttpStatus.OK);
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

    @GetMapping("/search")
    public ResponseEntity<?> searchMovieByTitleAndGenre(@RequestParam(value="title",required = false) String title, @RequestParam(value="genre",required = false) Movie.Genre genre) {
        return new ResponseEntity<>(movieService.searchMovieByTitleAndGenre(title,genre), HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public ResponseEntity<?> getMovieByGenre(@RequestParam("genre") String genre) {
//        return new ResponseEntity<>(movieService.getByGenre(genre), HttpStatus.OK);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Movie movie) {
        try {
            Movie addedMovie = movieService.add(movie);
            return new ResponseEntity<>(addedMovie, HttpStatus.OK);
        } catch (DuplicateIdFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Movie movie) {
        try {
            Movie updatedMovie = movieService.update(movie);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{movieId}")
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
