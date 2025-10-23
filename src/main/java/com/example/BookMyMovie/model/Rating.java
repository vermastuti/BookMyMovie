package com.example.BookMyMovie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.Date;

@Entity
public class Rating {


    @Id
    Integer ratingId;

    @NotNull(message = "movieName is required")
    @Size(min = 4 , max = 20 , message = "Min size is 6 and max size is 20")
    String movieName;

    @NotNull(message = "rating is required")
    @Min(value = 1 , message = "Rating must be at least 1")
    @Max(value = 10 , message = "Rating cannot be more than 10")
    Integer rating;

    @NotNull(message = "movieId is required")
    Integer movieId;

    @NotNull(message = "customerId is required")
    Integer customerId;

    @NotNull(message = "review is required")
    @Size(min = 6 , max = 50 , message = "Min size is 20 and max size is 50")
    String review;

    @NotNull(message = "date is required")
    Date dateOfReviewCreated;

    //default constructor
    public Rating() {

    }

    //parametrized constructor
    public Rating(Integer ratingId, String movieName, Integer rating, Integer movieId, Integer customerId, String review, Date dateOfReviewCreated) {
        this.ratingId = ratingId;
        this.movieName = movieName;
        this.rating = rating;
        this.movieId = movieId;
        this.customerId = customerId;
        this.review = review;
        this.dateOfReviewCreated = dateOfReviewCreated;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", movieName='" + movieName + '\'' +
                ", rating=" + rating +
                ", movieId=" + movieId +
                ", customerId=" + customerId +
                ", review='" + review + '\'' +
                ", dateOfReviewCreated=" + dateOfReviewCreated +
                '}';
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDateOfReviewCreated() {
        return dateOfReviewCreated;
    }

    public void setDateOfReviewCreated(Date dateOfReviewCreated) {
        this.dateOfReviewCreated = dateOfReviewCreated;
    }
}

