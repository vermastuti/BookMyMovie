package com.example.BookMyMovie.service;

import com.example.BookMyMovie.model.Movie;

import java.util.List;

public interface IMovieService {

    Movie add(Movie movie);

    Movie update(Movie movie);

    Movie getById(int id);

    List<Movie> getAll();

    List<Movie> getByTitle(String title);

    List<Movie> getByGenre(String genre);

    Object getShowsByMovie();
}
