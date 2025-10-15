package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.repository.BookingRepository;
import com.example.BookMyMovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements iBookingService{

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
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
        return bookingRepo.findByUserProfileId(userId);
    }

    @Override
    public Booking addNewBooking(Booking booking){
        boolean isPresent = bookingRepo.existsById(booking.getBookingId());
        if(isPresent){
            throw new DuplicateIdFoundException("Duplicate Id");
        } else {
            return bookingRepo.save(booking);
        }
    }
}
