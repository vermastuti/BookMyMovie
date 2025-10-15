package com.example.BookMyMovie.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "booking")
@EntityListeners(AuditingEntityListener.class)
public class Booking {

    public enum Status
    {
        CONFIRMED,
        CANCELLED,
        PENDING
    }

    @Id
    private int bookingId;
    private int userProfileId;
    private int movieShowId;
    private int seats;
    private double amount;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean isPaid;


    public Booking(){}

    public Booking(int userProfileId, int movieShowId, int bookingId, double amount, Status status, Boolean isPaid, int seats) {
        this.userProfileId = userProfileId;
        this.bookingId = bookingId;
        this.seats = seats;
        this.movieShowId = movieShowId;
        this.amount = amount;
        this.status = status;
        this.isPaid = isPaid;
    }

    public int getUserProfile() {
        return userProfileId;
    }

    public void setUserProfile(int userProfileId) {
        this.userProfileId = userProfileId;
    }

    public int getShow() {
        return movieShowId;
    }

    public void setShow(int movieShow) {
        this.movieShowId = movieShow;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public int getSeats() {return seats;}

    public void setSeats(int seats) {this.seats = seats;}
}
