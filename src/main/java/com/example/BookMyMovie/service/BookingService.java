package com.example.BookMyMovie.service;

import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookingService implements iBookingService{
    @Autowired
    private BookingRepository bookingRepo;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public void cancelBooking(int bookingId) {

    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        return bookingRepo.findByUserId(userId);
    }
}
