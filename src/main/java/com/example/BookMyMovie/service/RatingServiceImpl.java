package com.example.BookMyMovie.service;

//package com.example.MovieRating.service;
//import com.example.MovieRating.model.Rating;
//import com.example.MovieRating.repository.RatingRepository;

import com.example.BookMyMovie.model.Rating;
import com.example.BookMyMovie.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {


    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating addReview(Rating rating) {

        Rating ratingcreated = ratingRepository.save(rating);

        return ratingcreated;

    }


    @Override
    public List<Rating> viewAllRatings() {

        List<Rating> ratings = ratingRepository.findAll();

        return ratings;

    }


    @Override
    public List<Rating> viewAllRatingsByCutomerId(Integer customerId) {

        List<Rating> ratingListByCustomerId = ratingRepository.findByCustomerId(customerId);

        //ratingListByCustomerId.stream().forEach((c)-> c.toString());
        return ratingListByCustomerId;
    }


    //yet to implement
    @Override
    public List<Rating> viewAllRatingsByMovieId(Integer movieId) {

        List<Rating> ratingsByMovieId = ratingRepository.findByMovieId(movieId);


        return ratingsByMovieId;
    }

    public Rating updateRating(Integer ratingId, Integer customerId, Integer newRating, String newReview) {

        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found with given Id ratingId : " + ratingId));

        // Update fields
        rating.setReview(newReview);
        rating.setRating(newRating);

        return ratingRepository.save(rating);
    }

}



