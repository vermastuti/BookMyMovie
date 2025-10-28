package com.example.BookMyMovie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

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
    @NotNull(message = "User Email is mandatory")
    private String email;
    @NotNull(message = "Movie show ID is mandatory")
    private Integer movieShowId;

    @NotNull(message = "Movie Title can't be null")
    private String movieTitle;

    @NotNull(message = "Show date should be valid")
    LocalDate showDate;

    @NotNull(message = "Show time should be valid")
    LocalTime showTime;
    @NotNull(message = "Seats are mandatory")
    @Min(value = 1, message = "At least one seat must be booked")
    private Integer seats = 0;
    @Positive(message="price should be positive")
    @NotNull(message = "Can't be Null")
    private Integer amount;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Booking status is mandatory")
    private Status status= Status.PENDING;
    private boolean isPaid = false;


    public Booking() {
    }

    public Booking(int bookingId, String email, Integer movieShowId, String movieTitle, LocalDate showDate, LocalTime showTime, Integer seats, Integer amount, Status status, boolean isPaid) {
        this.bookingId = bookingId;
        this.email = email;
        this.movieShowId = movieShowId;
        this.movieTitle = movieTitle;
        this.showDate = showDate;
        this.showTime = showTime;
        this.seats = seats;
        this.amount = amount;
        this.status = status;
        this.isPaid = isPaid;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMovieShowId() {
        return movieShowId;
    }

    public void setMovieShowId(Integer movieShowId) {
        this.movieShowId = movieShowId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
