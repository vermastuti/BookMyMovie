package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.service.iBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookingController {
    @Autowired
    private iBookingService bookingService;


    @GetMapping("/view")
    public ResponseEntity<?> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/user/{pid}")
    public ResponseEntity<?> getAllBookingsByUserId(@PathVariable("pid") int pid){
        return new ResponseEntity<>(bookingService.getBookingsByUserId(pid), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        try {
            Booking booking1 = bookingService.addNewBooking(booking);
            return new ResponseEntity<>(booking1, HttpStatus.CREATED);
        } catch (DuplicateIdFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
