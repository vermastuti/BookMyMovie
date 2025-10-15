package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
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
    List<Booking> bookings=new ArrayList<Booking>();
    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
       booking.setBookingId(3);
       booking.setShow(1);
       booking.setAmount(200);
       booking.setPaid(true);
       booking.setUserProfile(1);


    }

    @Test
    public void testWhenAddBookingThenSuccess()
    {
        Mockito.when(repo.save(booking)).thenReturn(booking);

        Booking result=repo.save(booking);

        assertEquals(3,result.getBookingId());


        Mockito.verify(repo,Mockito.times(1)).save(booking);
    }


    @Test
    void testWhenNewBookingThenFail() {
        Mockito.when(repo.existsById(booking.getBookingId())).thenReturn(true);

        Exception exception= Assertions.assertThrows(DuplicateIdFoundException.class,()->service.addNewBooking(booking));
        assertEquals("Duplicate Id",exception.getMessage());

        Mockito.verify(repo,Mockito.times(0)).save(booking);


    }
}