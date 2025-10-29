package com.example.BookMyMovie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Collection;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty("id")
    Integer theatreId;
    String city;
    String name;

    public Theatre() {

    }

    public Theatre(Integer seats, String city, String name) {
        this.city = city;
        this.name = name;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTheatreId(int i) {

    }
}
