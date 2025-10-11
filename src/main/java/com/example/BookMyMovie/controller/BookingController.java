package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.service.iBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    private iBookingService bookingService;


    @GetMapping
    public ResponseEntity<?> getAllBookings(){

        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable int userId) {
        return new ResponseEntity<>(bookingService.getBookingsByUserId(userId),HttpStatus.OK);
    }
}
