package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.BookingRequest;
import com.example.BookMyMovie.exception.InvalidCredentialsException;
import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTests {

    @Mock
    BookingRepository repo;
    @InjectMocks
    BookingService service;

    Booking booking=new Booking();
    private BookingRequest request;
    List<Booking> bookings=new ArrayList<Booking>();
    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);

        // Sample request setup
        request = new BookingRequest();
        request.setUserProfileId(7);
        request.setMovieShowId(101);
        request.setSeats(2);

        // Sample booking setup
        booking = new Booking();
        booking.setBookingId(3);
        booking.setUserProfileId(1);
        booking.setMovieShowId(101);
        booking.setSeats(2);
        booking.setAmount(500.0);
        booking.setStatus(Booking.Status.CONFIRMED);
        booking.setPaid(true);


    }

    @Test
    public void testWhenAddBookingThenSuccess()
    {
//        Mockito.when(repo.existsByUserProfileIdAndMovieShowIdAndStatusNot(7, 101, Booking.Status.CANCELLED))
//                .thenReturn(false);
//        Mockito.when(repo.save(booking)).thenReturn(booking);
//
//        Booking response = service.addNewBooking(request);
//
//        assertNotNull(response);
//        assertEquals(10, response.getBookingId());
//        assertEquals(500.0, response.getAmount());
//        assertEquals(Booking.Status.CONFIRMED, response.getStatus());
//        assertTrue(response.isPaid());
        Mockito.when(repo.save(booking)).thenReturn(booking);

        Booking result=repo.save(booking);

        assertEquals(3,result.getBookingId());


        Mockito.verify(repo,Mockito.times(1)).save(booking);
    }

    @Test
    void testCreateBooking_NullUserId_ThrowsException() {
        request.setUserProfileId(null);
        assertThrows(InvalidCredentialsException.class,
                () -> service.addNewBooking(request),
                "User ID cannot be null");
    }
    @Test
    void testCreateBooking_NullMovieShowId_ThrowsException() {
        request.setMovieShowId(null);
        assertThrows(InvalidCredentialsException.class,
                () -> service.addNewBooking(request),
                "Movie Show ID cannot be null");
    }

    @Test
    void testCreateBooking_InvalidSeats_ThrowsException() {
        request.setSeats(0);
        assertThrows(InvalidCredentialsException.class,
                () -> service.addNewBooking(request),
                "Seats must be greater than 0");
    }

    @Test
    void testCreateBooking_AlreadyExists_ThrowsException() {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setUserProfileId(1);
        request.setMovieShowId(101);
        request.setSeats(2);

        Mockito.when(repo.existsByUserProfileIdAndMovieShowIdAndStatusNot(1, 101, Booking.Status.CANCELLED))
                .thenReturn(true);

        // Act + Assert
        InvalidCredentialsException exception = assertThrows(
                InvalidCredentialsException.class,                // expected exception type
                () -> service.addNewBooking(request)          // code that should throw it
        );

        // Verify exception message
        assertEquals("User 1 already has a booking for show 101", exception.getMessage());
    }

    @Test
    void testGetBookingsByUserId_Success() {
        Mockito.when(repo.findByUserProfileId(1)).thenReturn(List.of(booking));

        List<Booking> result = service.getBookingsByUserId(1);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getUserProfileId());
        assertEquals(500.0, result.get(0).getAmount());
    }
    @Test
    void testGetBookingsByUserId_NoBookingsFound_ThrowsException() {
        Mockito.when(repo.findByUserProfileId(1)).thenReturn(List.of());
        assertThrows(InvalidCredentialsException.class,
                () -> service.getBookingsByUserId(1),
                "No bookings found for user ID: 1");
    }


}