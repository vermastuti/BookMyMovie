package com.example.BookMyMovie.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

import static jakarta.persistence.GenerationType.IDENTITY;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MovieShow {

    public enum ShowStatus{
        Available,
        Cancelled
    };


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty("id")
    int showId;
    LocalDate showDate;
    LocalTime showTime;
    Integer movieId;
    Integer theatreId;
    Integer totalPrice;
    int availableSeats;

    @Enumerated(EnumType.STRING)
    ShowStatus status;
    Integer seats;

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public ShowStatus getStatus() {
        return status;
    }

    public void setStatus(ShowStatus status) {
        this.status = status;
    }

    public MovieShow() {
        this.status=ShowStatus.Available;
    }

    public MovieShow(LocalDate showDate, LocalTime showTime, Integer movieId, Integer theatreId, Integer totalPrice, int availableSeats, String status) {
        this.showDate = showDate;
        this.showTime = showTime;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.totalPrice = totalPrice;
        this.availableSeats = availableSeats;
        this.status = ShowStatus.Available;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getShowId() {
        return showId;
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

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

}
