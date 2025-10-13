package com.example.BookMyMovie.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Show {

    @Id
    Integer showId;
    LocalDate date;
    LocalTime time;

    @ManyToOne
    @JoinColumn(name = "movie_movie_id")
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_theatre_id")
    Theatre theatre;
    Integer totalPrice;
    Integer availableSeats;

    public Show() {}

    public Show(int showId, LocalDate date, LocalTime time, Movie movie, Theatre theatre, int totalPrice, int availableSeats) {
        this.showId = showId;
        this.date = date;
        this.time = time;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
