package com.example.BookMyMovie.controller;

//package com.example.MovieRating.controller;
//import com.example.MovieRating.model.Rating;
//import com.example.MovieRating.service.RatingService;

import com.example.BookMyMovie.model.Rating;
import com.example.BookMyMovie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody Rating rating) {

        Rating ratingresult = ratingService.addReview(rating);

        return (new ResponseEntity<>(ratingresult, HttpStatus.CREATED));

    }

    @GetMapping("/viewallratings")
    public ResponseEntity<?> viewAllRatings()
    {

        List<Rating> ratings = ratingService.viewAllRatings();

        return (new ResponseEntity<>(ratings,HttpStatus.OK));

    }

    @GetMapping("/allratingsbycustid/{id}")
    public ResponseEntity<?> viewAllRatingsByCutomerId(@PathVariable Integer id)
    {

        List<Rating> ratings = ratingService.viewAllRatingsByCutomerId(id);
        System.out.println("in controller");
        //ratings.stream().forEach((c)->c.toString());
        return (new ResponseEntity<>(ratings,HttpStatus.OK));


    }

    @GetMapping("/allratingsbymovid/{movieId}")
    public ResponseEntity<?> viewAllRatingsByMovieId(@PathVariable Integer movieId)
    {

        List<Rating> ratings = ratingService.viewAllRatingsByMovieId(movieId);
        System.out.println("in controller");
        ratings.stream().forEach((c)->c.toString());
        return (new ResponseEntity<>(ratings,HttpStatus.OK));

    }

    @PostMapping("/updaterating/{ratingId}/{customerId}/{rating}/{newReview}")
    public ResponseEntity<?> updateRating(@PathVariable Integer ratingId,@PathVariable Integer rating, @PathVariable Integer customerId,@PathVariable String newReview) {


        Rating updatedRating = ratingService.updateRating(ratingId, customerId , rating, newReview);

        return (new ResponseEntity<>(updatedRating,HttpStatus.OK));

    }
}
