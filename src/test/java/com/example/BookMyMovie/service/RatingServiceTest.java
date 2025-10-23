package com.example.BookMyMovie.service;


import com.example.BookMyMovie.exception.RatingIdAlreadyExistException;
import com.example.BookMyMovie.model.Rating;
import com.example.BookMyMovie.repository.RatingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class RatingServiceTest {

    @Mock
    RatingRepository ratingRepo;

    @InjectMocks
    RatingService ratingService;

    Rating rating;

    List<Rating> ratingList;

    @BeforeEach
    public void init() throws RatingIdAlreadyExistException {

        //Associate the Mockito with current class's instances
        MockitoAnnotations.openMocks(this);

        rating = new Rating();
        rating.setRating(2);
        rating.setReview("Go for it without second thought");
        rating.setRatingId(345);
        rating.setCustomerId(565);
        rating.setMovieName("Drishyam 2");
        rating.setMovieId(333);
        rating.setDateOfReviewCreated(new Date());

    }

    @Test
    public void whenRatingIdAlreadyNotPresentAndThenSuccess() throws RatingIdAlreadyExistException {

        when(ratingRepo.save(rating)).thenReturn(rating);

        Rating ratingObjAfterMocking = ratingService.addReview(rating);
        assertEquals(ratingObjAfterMocking.getMovieName(), "Drishyam 2");

        Mockito.verify(ratingRepo,Mockito.times(1)).save(rating);

    }

    @Test
    public void whenRatingIdAlreadyExistThenReturnException() {

        when(ratingRepo.existsById(rating.getRatingId())).thenReturn(true);

        Exception exception = Assertions.assertThrows(RatingIdAlreadyExistException.class,() -> ratingService.addReview(rating));

        assertEquals("Rating Id Already Present",exception.getMessage());

        Mockito.verify(ratingRepo,Mockito.times(0)).save(rating);

    }

}
