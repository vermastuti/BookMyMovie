package com.example.BookMyMovie.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
public class BookingRequest {


        @NotNull(message = "User ID is mandatory")
        private Integer userProfileId;

        @NotNull(message = "Movie show ID is mandatory")
        private Integer movieShowId;

        @NotNull(message = "Seats are mandatory")
        @Min(value = 1, message = "At least one seat must be booked")
        private Integer seats;

        // Getters and Setters
        public Integer getUserProfileId() {
            return userProfileId;
        }

        public void setUserProfileId(Integer userProfileId) {
            this.userProfileId = userProfileId;
        }

        public Integer getMovieShowId() {
            return movieShowId;
        }

        public void setMovieShowId(Integer movieShowId) {
            this.movieShowId = movieShowId;
        }

        public Integer getSeats() {
            return seats;
        }

        public void setSeats(Integer seats) {
            this.seats = seats;
        }
    }

