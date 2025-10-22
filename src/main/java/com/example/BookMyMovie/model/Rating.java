package com.example.BookMyMovie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Rating {


    @Id
    Integer ratingId;

    Integer rating;

    Integer movieId;

    Integer customerId;

    String review;

    Date dateOfReviewCreated;

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
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

