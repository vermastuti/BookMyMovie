package com.example.BookMyMovie.model;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;

@Entity
@Table(name="bookings")
public class Booking {

    private enum Status
    {
        CONFIRMED,
        CANCELLED,
        PENDING

    };


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private User user;
    private Show show;
    private List<Integer> seats;
    private double amount;
    private Status status;
    private boolean isPaid;


}
