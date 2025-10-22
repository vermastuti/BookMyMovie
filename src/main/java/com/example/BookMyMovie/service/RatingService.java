package com.example.BookMyMovie.service;

//package com.example.MovieRating.service;
//import com.example.MovieRating.model.Rating;

import com.example.BookMyMovie.model.Rating;

import java.util.List;


public interface RatingService {

    Rating addReview(Rating rating);

    List<Rating> viewAllRatings();

    List<Rating> viewAllRatingsByCutomerId(Integer customerId);

    List<Rating> viewAllRatingsByMovieId(Integer movieId);

    Rating updateRating(Integer ratingId , Integer rating , Integer customerId , String newReview);


}
