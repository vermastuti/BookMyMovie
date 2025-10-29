package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.BookingRequest;
import com.example.BookMyMovie.model.Booking;

import java.util.List;

public interface iBookingService {
    List<Booking> getAllBookings();
    void cancelBooking(int bookingId);
    List<Booking> getBookingsByEmail(String email);

    Booking addNewBookings(BookingRequest requestDTO);
}
