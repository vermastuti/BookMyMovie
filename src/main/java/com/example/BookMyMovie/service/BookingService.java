package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.BookingRequest;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.exception.InvalidCredentialsException;
import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.model.UserProfile;
import com.example.BookMyMovie.repository.BookingRepository;
import com.example.BookMyMovie.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
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
    public void cancelBooking(int bookingId) {
        // Check if the booking exists
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new IdDoesNotExistException("Booking ID " + bookingId + " not found"));

        // Check if already cancelled
        if (booking.getStatus() == Booking.Status.CANCELLED) {
            throw new InvalidCredentialsException("Booking ID " + bookingId + " is already cancelled");
        }

        // Update booking details
        booking.setStatus(Booking.Status.CANCELLED);
        booking.setPaid(false); // mark unpaid; refund logic can be added later

        // Save updated booking
        bookingRepo.save(booking);
    }

    @Override
    public List<Booking> getBookingsByEmail(String  email) {

        if (email == null) {
            throw new InvalidCredentialsException("User email cannot be null");
        }

        List<Booking> bookings = bookingRepo.findByEmail(email);

        if (bookings.isEmpty())
            throw new InvalidCredentialsException("No bookings found for user Email: " + email);
        else{

        }
        return bookingRepo.findByEmail(email);
    }

    @Override
    public Booking addNewBooking(BookingRequest requestDTO){
        if (requestDTO.getEmail() == null) {
            throw new InvalidCredentialsException("User ID cannot be null");
        }
        if (requestDTO.getMovieShowId() == null) {
            throw new InvalidCredentialsException("Movie Show ID cannot be null");
        }

        if (requestDTO.getSeats() == null || requestDTO.getSeats() <= 0) {
            throw new InvalidCredentialsException("Seats must be greater than 0");
        }
        boolean alreadyExists = bookingRepo
                .existsByEmailAndMovieShowIdAndStatus(
                        requestDTO.getEmail(),
                        requestDTO.getMovieShowId(),
                        Booking.Status.CONFIRMED);
        System.out.println(alreadyExists);
        if (alreadyExists)
            throw new DuplicateIdFoundException(
                    String.format("User %s already has a booking for show %s",
                            requestDTO.getEmail(), requestDTO.getMovieShowId()));
            // Map DTO → Entity
            Booking booking = new Booking();
            booking.setEmail(requestDTO.getEmail());
            booking.setMovieShowId(requestDTO.getMovieShowId());
            booking.setMovieTitle(requestDTO.getMovieTitle());
            booking.setShowDate(requestDTO.getShowDate());
            booking.setShowTime(requestDTO.getShowTime());
            booking.setSeats(requestDTO.getSeats());
            booking.setAmount(requestDTO.getAmount());
            booking.setStatus(Booking.Status.CONFIRMED);
            booking.setPaid(true);

            Booking savedBooking = bookingRepo.save(booking);
        return savedBooking;
//            // Map Entity → Response DTO
//            Booking responseDTO = new Booking();
//            responseDTO.setBookingId(savedBooking.getBookingId());
//            responseDTO.setEmail(savedBooking.getEmail());
//            responseDTO.setMovieShowId(savedBooking.getMovieShowId());
//            responseDTO.setSeats(savedBooking.getSeats());
//            responseDTO.setAmount(savedBooking.getAmount());
//            responseDTO.setStatus(savedBooking.getStatus());
//            responseDTO.setPaid(savedBooking.isPaid());
//
//            return responseDTO;
       //  }
    }
}
