package com.example.BookMyMovie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;
    @NotNull(message = "User ID is mandatory")
    private Integer userProfileId;
    @NotNull(message = "Movie show ID is mandatory")
    private Integer movieShowId;

    @NotNull(message = "Seats are mandatory")
    @Min(value = 1, message = "At least one seat must be booked")
    private Integer seats = 0;
    private double amount;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Booking status is mandatory")
    private Status status= Status.PENDING;
    private boolean isPaid = false;


    public Booking() {
    }

    public Booking(int bookingId, Integer userProfileId, Integer movieShowId, Integer seats, double amount, Status status, boolean isPaid) {
        this.bookingId = bookingId;
        this.userProfileId = userProfileId;
        this.movieShowId = movieShowId;
        this.seats = seats;
        this.amount = amount;
        this.status = status;
        this.isPaid = isPaid;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Integer getUserProfileId() {
        return userProfileId;
    }

    public Integer getMovieShowId() {
        return movieShowId;
    }

    public Integer getSeats() {
        return seats;
    }

    public double getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setUserProfileId(Integer userProfileId) {
        this.userProfileId = userProfileId;
    }

    public void setMovieShowId(Integer movieShowId) {
        this.movieShowId = movieShowId;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
