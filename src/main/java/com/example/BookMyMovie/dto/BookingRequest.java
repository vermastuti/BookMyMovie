package com.example.BookMyMovie.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequest {


        @NotNull(message = "User Email is mandatory")
        private String email;

        @NotNull(message = "Movie show ID is mandatory")
        private Integer movieShowId;
        @NotNull(message = "Movie Title can't be null")
        private String movieTitle;

        @NotNull(message = "Show date should not be null")
        LocalDate showDate;
        @NotNull(message = "Show time should not be null")
        LocalTime showTime;

        @NotNull(message = "Seats are mandatory")
        @Min(value = 1, message = "At least one seat must be booked")
        private Integer seats;
        @Positive(message="price should be positive")
        @NotNull(message = "Can't be Null")
        private Integer amount;

    // Getters and Setters


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
}

