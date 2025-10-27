package com.example.BookMyMovie.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public class MovieShowDto {

    @NotNull(message = "movie id is mandatory")
    private Integer movieId;
    @NotNull(message = "theatre id is mandatory")
    private Integer theatreId;
    @NotNull(message = "show time is mandatory")
    private LocalTime showTime;
    @NotNull(message = "show date is mandatory")
    private LocalDate showDate;
    @Positive(message="price should be positive")
    @NotNull(message = "Can't be Null")
    private Integer totalPrice;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }
}
