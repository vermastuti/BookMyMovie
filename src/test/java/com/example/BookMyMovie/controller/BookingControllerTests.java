package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.dto.BookingRequest;
import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.security.test.context.support.WithMockUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService service;

    private Booking booking;
    private BookingRequest bookingRequest;

    @BeforeEach
    public void init() {
        booking = new Booking();
        booking.setBookingId(3);
        booking.setMovieShowId(1);
        booking.setSeats(2);
        booking.setAmount(500);
        booking.setPaid(true);
        booking.setUserProfileId(1);
        booking.setStatus(Booking.Status.CONFIRMED);

        bookingRequest = new BookingRequest();
        bookingRequest.setUserProfileId(1);
        bookingRequest.setMovieShowId(1);
        bookingRequest.setSeats(2);
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void whenAddThenReturnSuccess() throws Exception {
        Mockito.when(service.addNewBooking(any(BookingRequest.class))).thenReturn(booking);

        ObjectMapper objectMapper = new ObjectMapper();
        String bookingJson = objectMapper.writeValueAsString(booking);

        mockMvc.perform(post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").value(3));

        Mockito.verify(service, times(1)).addNewBooking(any(BookingRequest.class));
    }
}