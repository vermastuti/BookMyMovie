package com.example.BookMyMovie.repository;

//package com.example.MovieRating.repository;
//import com.example.MovieRating.model.Rating;

import com.example.BookMyMovie.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    List<Rating> findByCustomerId(Integer customerId);

    List<Rating> findByMovieId(Integer movieId);

}
