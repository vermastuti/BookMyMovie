package com.example.BookMyMovie.service;

import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.repository.BookingRepository;
import com.example.BookMyMovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements iBookingService{

    private BookingRepository bookingRepo;
    private UserRepository userRepo;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public void cancelBooking(int bookingId) {

    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        return bookingRepo.findByUserProfile(userRepo.findById(userId).get());
    }
}
