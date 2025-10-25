package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.dto.MovieShowDto;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
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
@Validated
public class ShowController {

    @Autowired
    ShowService showService;

    @Autowired
    MovieService movieService;


    @PostMapping("/add")
    public ResponseEntity<?> addShow(@RequestBody MovieShowDto movieShowDto) throws DuplicateIdFoundException {
        MovieShow movieShowResult = showService.add(movieShowDto);
        return new ResponseEntity<>(movieShowResult, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("🎯 Controller is working!");
        return "OK";
    }


    @GetMapping("/all")
    public ResponseEntity<?> viewallshow() {
        List<MovieShow> movieShows = showService.viewAllShow();
        return new ResponseEntity<>(movieShows, HttpStatus.OK);
    }

    @GetMapping("/viewall/{mid}")
    public ResponseEntity<?> viewByMovieId(@PathVariable("mid") int mid) {
        try {
            List<MovieShow> movieShows = showService.findByMovieId(mid);
            return new ResponseEntity<>(movieShows, HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cancel/{showId}")
    public ResponseEntity<?> cancelshow(@PathVariable("showId") int showId) {
        try {
//            showService.cancelShow(showId);
            //bookingService.cancelBooking(showId);
            return new ResponseEntity<>("Shows cancelled", HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
