package com.example.BookMyMovie.controller;


import com.example.BookMyMovie.exception.ShowIdAlreadyExistException;
import com.example.BookMyMovie.model.MovieShow;
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
    @PostMapping("/add")
    public ResponseEntity<?> addShow(@RequestBody MovieShow movieShow)
    {
        try {
            MovieShow movieShowResult = showService.add(movieShow);
            return  new ResponseEntity<>(movieShowResult, HttpStatus.CREATED);
        } catch (ShowIdAlreadyExistException e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/viewall")
    public  ResponseEntity<?> viewallshow()
    {
        List<MovieShow> movieShows =showService.viewAllShow();
        return  new ResponseEntity<>(movieShows,HttpStatus.OK);
    }
}
