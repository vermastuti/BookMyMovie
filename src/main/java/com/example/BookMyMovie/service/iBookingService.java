package com.example.BookMyMovie.service;

import com.example.BookMyMovie.model.Booking;

import java.util.List;

public interface iBookingService {
    List<Booking> getAllBookings();
    void cancelBookings(int bookingId);

//    void cancelBookingsByMovieId(int movieId);

    List<Booking> getBookingsByUserId(int userId);

    Booking addNewBooking(Booking booking);
}
