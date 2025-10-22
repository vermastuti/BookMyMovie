package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.RatingIdAlreadyExistException;
import com.example.BookMyMovie.exception.RatingIdNotFoundException;
import com.example.BookMyMovie.model.Rating;

import java.util.List;


public interface iRatingService {

    Rating addReview(Rating rating) throws RatingIdAlreadyExistException;

    List<Rating> viewAllRatings();

    List<Rating> viewAllRatingsByCutomerId(Integer customerId) ;

    List<Rating> viewAllRatingsByMovieId(Integer movieId);

    Rating updateRating(Integer ratingId , Integer rating , Integer customerId , String newReview) throws RatingIdNotFoundException;


}
