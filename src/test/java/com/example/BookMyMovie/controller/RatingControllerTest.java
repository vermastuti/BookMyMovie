package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.exception.RatingIdAlreadyExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.Rating;
import com.example.BookMyMovie.service.RatingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.mockito.Mockito.when;

public class RatingControllerTest {

    @Mock
    RatingService ratingService;

    @InjectMocks
    RatingController ratingController;

    Rating rating = new Rating();

    MockMvc mockmvc;
    List<Rating> ratings=new ArrayList<Rating>();

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
        mockmvc= MockMvcBuilders.standaloneSetup(ratingController).build();

        rating.setRatingId(101);
        rating.setMovieName("Raazi");
        rating.setCustomerId(201);
        rating.setRating(8);
        rating.setMovieId(555);
        rating.setReview("Very Nice Movie A Must Watch");
        rating.setDateOfReviewCreated(new Date());


    }

    @Test
    public void whenAddRatingThenReturnSuccess() throws Exception

    {
        //STUBBING

        when(ratingService.addReview(any())).thenReturn(rating);

        mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/movie/rating")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObject(rating)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(ratingService,times(1)).addReview(any());
    }

    @Test
    public void whenRatingIdAlreadyExistAndAddingTheRatingThenReturnFail() throws Exception
    {
        when(ratingService.addReview(any())).thenThrow(RatingIdAlreadyExistException.class);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/movie/rating")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObject(rating)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }


    public  String convertObject(Object object) throws JsonProcessingException {
        ObjectMapper obmapper=new ObjectMapper();
        String result= obmapper.writeValueAsString(object);
        return result;
    }








}
