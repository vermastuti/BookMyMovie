package com.example.BookMyMovie.controller;


import com.example.BookMyMovie.exception.ShowIdAlreadyExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.Show;
import com.example.BookMyMovie.service.ShowService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> addShow(@RequestBody Show show)
    {
        try {
            Show showResult = showService.add(show);
            return  new ResponseEntity<>(showResult, HttpStatus.CREATED);
        } catch (ShowIdAlreadyExistException e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/viewall")
    public  ResponseEntity<?> viewallshow()
    {
        List<Show> shows=showService.viewAllShow();
        return  new ResponseEntity<>(shows,HttpStatus.OK);
    }
}
