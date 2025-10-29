package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.dto.MovieShowDto;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.MovieShow;
import com.example.BookMyMovie.service.BookingService;
import com.example.BookMyMovie.service.MovieService;
import com.example.BookMyMovie.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@Validated
@CrossOrigin(
        exposedHeaders = "Content-Range"
)
public class ShowController {

    @Autowired
    ShowService showService;

    @Autowired
    MovieService movieService;

    @Autowired
    BookingService bookingService;


    @PostMapping
    public ResponseEntity<?> addShow(@Valid @RequestBody MovieShowDto movieShowDto) throws DuplicateIdFoundException {
        MovieShow movieShowResult = showService.add(movieShowDto);
        return new ResponseEntity<>(movieShowResult, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> viewallshow() {
        List<MovieShow> movieShows = showService.viewAllShow();
        return new ResponseEntity<>(movieShows, HttpStatus.OK);
    }

    @GetMapping("/movie/{mid}")
    public ResponseEntity<?> viewByMovieId(@PathVariable("mid") int mid) {
        try {
            List<MovieShow> movieShows = showService.findByMovieId(mid);
            return new ResponseEntity<>(movieShows, HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{pid}")
    public ResponseEntity<?> viewByShowId(@PathVariable("pid") int mid) {
        try {
            MovieShow movieShow = showService.findByShowId(mid);
            return new ResponseEntity<>(movieShow, HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{pid}")
    public ResponseEntity<?> update(@Valid @RequestBody MovieShow movieShow, @PathVariable("pid") int pid) {
        try {
            movieShow.setShowId(pid);
            MovieShow updatedMovieShow = showService.update(movieShow);
            return new ResponseEntity<>(updatedMovieShow, HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/reduceseats/{showId}/{seats}")
    public ResponseEntity<?> reduceSeatsByshowId(@PathVariable("showId") int showId,@PathVariable("seats") int seats) {
        try {
            showService.reduceSeatsByshowId(showId,seats);
            return new ResponseEntity<>("Shows seats decresed", HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/incrementseats/{showId}/{seats}")
    public ResponseEntity<?> increseSeatByshowId(@PathVariable int showId,@PathVariable int seats) {
        try {
            showService.increseSeatByshowId(showId,seats);
            return new ResponseEntity<>("Shows seats incresed", HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/cancelByMovieId{movieId}")
//    public ResponseEntity<?> cancelByMovieId(@PathVariable int movieId) {
//        try {
//            showService.cancelShowsByMovieId(movieId);
//            return new ResponseEntity<>("Shows canceled", HttpStatus.OK);
//        } catch (IdDoesNotExistException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (ShowCantCancelled e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @PutMapping("/cancelByShowId{movieId}")
//    public ResponseEntity<?> cancelByShowId(@PathVariable int showId) {
//        try {
//            showService.cancelShowbyShowId(showId);
//            return new ResponseEntity<>("Shows canceled", HttpStatus.OK);
//        } catch (IdDoesNotExistException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (ShowCantCancelled e) {
//            throw new RuntimeException(e);
//        }
//    }
}
