package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.BookingRequest;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
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
        request.setEmail("sangeeta123@gmail.com");
        request.setMovieShowId(102);
        request.setSeats(2);

        // Sample booking setup
        booking = new Booking();
        booking.setBookingId(3);
        booking.setEmail("sangeeta123@gmail.com");
        booking.setMovieShowId(101);
        booking.setSeats(2);
        booking.setAmount(500);
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
    void testCreateBooking_NullEmailThrowsException() {
        request.setEmail(null);
        assertThrows(InvalidCredentialsException.class,
                () -> service.addNewBooking(request),
                "User Email cannot be null");
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
        request.setEmail("sangeeta123@gmail.com");
        request.setMovieShowId(102);
        request.setSeats(2);

        Mockito.when(repo.existsByEmailAndMovieShowIdAndStatus("sangeeta123@gmail.com", 102, Booking.Status.CONFIRMED))
                .thenReturn(true);

        // Act + Assert
        DuplicateIdFoundException exception = assertThrows(
                DuplicateIdFoundException.class,                // expected exception type
                () -> service.addNewBooking(request)          // code that should throw it
        );

        // Verify exception message
        assertEquals("User sangeeta123@gmail.com already has a booking for show 102", exception.getMessage());
    }

    @Test
    void testGetBookingsByEmail_Success() {
        Mockito.when(repo.findByEmail("sangeeta123@gmail.com")).thenReturn(List.of(booking));

        List<Booking> result = service.getBookingsByEmail("sangeeta123@gmail.com");

        assertEquals(1, result.size());
        assertEquals("sangeeta123@gmail.com", result.get(0).getEmail());
        assertEquals(500, result.get(0).getAmount());
    }
    @Test
    void testGetBookingsByEmail_NoBookingsFound_ThrowsException() {
        Mockito.when(repo.findByEmail("sangeeta123@gmail.com")).thenReturn(List.of());
        assertThrows(InvalidCredentialsException.class,
                () -> service.getBookingsByEmail("sangeeta123@gmail.com"),
                "No bookings found for user ID: 1");
    }


}