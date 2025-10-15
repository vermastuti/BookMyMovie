package com.example.BookMyMovie.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Seat {

    @Id
    String seatNumber;
    int bookingId;
    int showId;
    boolean isAvailable;

    public Seat(){}

    public Seat(String seatNumber, int bookingId, int showId, boolean isAvailable) {
        this.seatNumber = seatNumber;
        this.bookingId = bookingId;
        this.showId = showId;
        this.isAvailable = isAvailable;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
