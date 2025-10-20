package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.BookingRequest;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.InvalidBookingException;
import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.repository.BookingRepository;
import com.example.BookMyMovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements iBookingService{

    private static final double SEAT_PRICE = 250.0;
    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public void cancelBookings(int showId) {
        List<Booking> bookings = bookingRepo.findByMovieShowId(showId);
        for(Booking cancelBooking: bookings){
            cancelBooking.setStatus(Booking.Status.CANCELLED);
            bookingRepo.save(cancelBooking);
        }
    }

    @Override
    public List<Booking> getBookingsByUserId(Integer userId) {

        if (userId == null) {
            throw new InvalidBookingException("User ID cannot be null");
        }

        List<Booking> bookings = bookingRepo.findByUserProfileId(userId);
        if (bookings.isEmpty())
            throw new InvalidBookingException("No bookings found for user ID: " + userId);
        return bookingRepo.findByUserProfileId(userId);
    }

    @Override
    public Booking addNewBooking(BookingRequest requestDTO){
        if (requestDTO.getUserProfileId() == null) {
            throw new InvalidBookingException("User ID cannot be null");
        }
        if (requestDTO.getMovieShowId() == null) {
            throw new InvalidBookingException("Movie Show ID cannot be null");
        }

        if (requestDTO.getSeats() == null || requestDTO.getSeats() <= 0) {
            throw new InvalidBookingException("Seats must be greater than 0");
        }
        boolean alreadyExists = bookingRepo
                .existsByUserProfileIdAndMovieShowIdAndStatusNot(
                        requestDTO.getUserProfileId(),
                        requestDTO.getMovieShowId(),
                        Booking.Status.CANCELLED);

        if (alreadyExists)
            throw new InvalidBookingException(
                    String.format("User %d already has a booking for show %d",
                            requestDTO.getUserProfileId(), requestDTO.getMovieShowId()));
            // Map DTO → Entity
            Booking booking = new Booking();
            booking.setUserProfileId(requestDTO.getUserProfileId());
            booking.setMovieShowId(requestDTO.getMovieShowId());
            booking.setSeats(requestDTO.getSeats());
            booking.setAmount(requestDTO.getSeats() * SEAT_PRICE);
            booking.setStatus(Booking.Status.CONFIRMED);
            booking.setPaid(true);

            Booking savedBooking = bookingRepo.save(booking);

            // Map Entity → Response DTO
            Booking responseDTO = new Booking();
            responseDTO.setBookingId(savedBooking.getBookingId());
            responseDTO.setUserProfileId(savedBooking.getUserProfileId());
            responseDTO.setMovieShowId(savedBooking.getMovieShowId());
            responseDTO.setSeats(savedBooking.getSeats());
            responseDTO.setAmount(savedBooking.getAmount());
            responseDTO.setStatus(savedBooking.getStatus());
            responseDTO.setPaid(savedBooking.isPaid());

            return responseDTO;
       //  }
    }



}
