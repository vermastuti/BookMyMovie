package com.example.BookMyMovie.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TheatreDTO {


    @NotNull(message = "Mandatory field")
    @Min(value=50 ,message = "seats must be greater than 50")
    Integer seats;
    @NotNull(message = "Mandatory field")
    String city;
    @NotNull(message = "Mandatory field")
    String name;

    public TheatreDTO() {
    }
    public TheatreDTO(Integer seats, String city, String name) {
        this.seats = seats;
        this.city = city;
        this.name = name;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
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
}
