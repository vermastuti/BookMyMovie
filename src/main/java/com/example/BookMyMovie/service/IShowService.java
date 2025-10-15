package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.ShowIdAlreadyExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.MovieShow;

import java.util.List;

public interface IShowService {
    MovieShow add(MovieShow movieShow) throws ShowIdAlreadyExistException;
    List<MovieShow> viewAllShow();
    List<MovieShow> findByMovie(Movie movie);

}
