package com.example.BookMyMovie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="seats")
public class Seat {
    @Id
    @GeneratedValue
    private int seatId;

    private Show show;

    private String seatNumber;
    //private boolean booked;
}
