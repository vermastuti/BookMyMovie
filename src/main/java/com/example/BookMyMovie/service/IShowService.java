package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.MovieShowDto;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.ShowCanNotBeCancelled;
import com.example.BookMyMovie.model.MovieShow;

import java.util.List;

public interface IShowService {
    MovieShow add(MovieShowDto movieShowDto) throws DuplicateIdFoundException;
    List<MovieShow> viewAllShow();
    List<MovieShow> findByMovieId(Integer movieId);
    void cancelShowByShowId(int showId) throws ShowCanNotBeCancelled;
    void cancelShowsByMovieId(Integer movieId) throws ShowCanNotBeCancelled;
    void  reduceSeatsByshowId(Integer showId ,Integer seat);
    void  increseSeatByshowId(Integer showId ,Integer seat);

}
