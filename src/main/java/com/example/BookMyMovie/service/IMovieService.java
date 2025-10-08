package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Movie;

import java.util.List;

public interface IMovieService {

    Movie add(Movie movie);
    Movie update(Movie movie);
    Movie getById(int id);
    List<Movie> getAll();
    Movie getByTitle(String title);
    List<Movie> getByTitleLike(String title);

}
