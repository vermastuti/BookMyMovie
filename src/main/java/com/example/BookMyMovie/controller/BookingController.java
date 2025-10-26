package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.dto.BookingRequest;
import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.service.iBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(
        exposedHeaders = "Content-Range"
)
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
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingRequest booking) {
        return new ResponseEntity<>(bookingService.addNewBooking(booking), HttpStatus.CREATED);
    }


}
