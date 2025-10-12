package com.example.BookMyMovie.model;

import jakarta.persistence.*;

@Entity
@Table
public class Theatre {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int theatreId;
    int totalSeats;
    @ManyToOne
    Show show;
    String address;
    String theatreName;

    public Theatre(int theatreId, int totalSeats, Show show, String address, String theatreName) {
        this.theatreId = theatreId;
        this.totalSeats = totalSeats;
        this.show = show;
        this.address = address;
        this.theatreName = theatreName;
    }

    public Theatre() {

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

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
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
}
