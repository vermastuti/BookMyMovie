package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.RatingIdAlreadyExistException;
import com.example.BookMyMovie.exception.RatingIdNotFoundException;
import com.example.BookMyMovie.model.Rating;
import com.example.BookMyMovie.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements iRatingService {

    @Autowired
    RatingRepository ratingRepository;


    @Override
    public Rating addReview(Rating rating) throws RatingIdAlreadyExistException {
        boolean isRatingExist = ratingRepository.existsById(rating.getRatingId());

        if (isRatingExist) {

            throw new RatingIdAlreadyExistException("Rating Id Already Present");

        } else {
            Rating ratingadded = ratingRepository.save(rating);

            return ratingadded;
        }
    }

    @Override
    public List<Rating> viewAllRatings() {

        List<Rating> ratings = ratingRepository.findAll();

        return ratings;

    }

    @Override
    public List<Rating> viewAllRatingsByCutomerId(Integer customerId) {

        List<Rating> ratingListByCustomerId;
        if (customerId !=null && customerId != 0 )  {

            ratingListByCustomerId = ratingRepository.findByCustomerId(customerId);

            return ratingListByCustomerId;
        } else

            return Collections.emptyList();

    }

    @Override
    public List<Rating> viewAllRatingsByMovieId(Integer movieId) {

        List<Rating> ratingsByMovieId;

        if (movieId != null && movieId !=0 ) {

            ratingsByMovieId = ratingRepository.findByMovieId(movieId);

            return ratingsByMovieId;
        } else
            return Collections.emptyList();

    }

    @Override
    public Rating updateRating(Integer ratingId, Integer newRating, Integer customerId, String newReview) throws RatingIdNotFoundException {
        Optional<Rating> ratingOptional = ratingRepository.findById(ratingId);

        if (ratingOptional.isPresent()) {
            ratingOptional.get().setReview(newReview);
            ratingOptional.get().setRating(newRating);

            Rating updatedRating = ratingOptional.get();

            return (ratingRepository.save(updatedRating));
        } else
            throw new RatingIdNotFoundException("RatingId doesn't exist");


        /*Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found with given Id ratingId : " + ratingId));
*/
        /*// Update fields
        rating.setReview(newReview);
        rating.setRating(newRating);
*/
        //return ratingRepository.save(rating);
    }

}

