package com.example.BookMyMovie.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer theatreId;
    Integer totalSeats;
    String address;
    String theatreName;

    @OneToMany(mappedBy = "theatre")
    private Collection<Show> shows;

    public Theatre() {
    }

    public Theatre(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public Theatre(Integer theatreId, Integer totalSeats, String address, String theatreName, Collection<Show> shows) {
        this.theatreId = theatreId;
        this.totalSeats = totalSeats;
        this.address = address;
        this.theatreName = theatreName;
        this.shows = shows;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public Collection<Show> getShows() {
        return shows;
    }

    public void setShows(Collection<Show> show) {
        this.shows = show;
    }
}
