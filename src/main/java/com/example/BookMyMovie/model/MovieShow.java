package com.example.BookMyMovie.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class MovieShow {

    @Id
    int showId;
    LocalDate showDate;
    LocalTime showTime;

    @ManyToOne
    @JoinColumn(name = "movie_movie_id")
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_theatre_id")
    Theatre theatre;
    int totalPrice;
    int availableSeats;

    public MovieShow() {}

    public MovieShow(int showId, LocalDate showDate, LocalTime showTime, Movie movie, Theatre theatre, int totalPrice, int availableSeats) {
        this.showId = showId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.movie = movie;
        this.theatre = theatre;
        this.totalPrice = totalPrice;
        this.availableSeats = availableSeats;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

}
