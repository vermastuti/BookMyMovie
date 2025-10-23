package com.example.BookMyMovie.service;

import com.example.BookMyMovie.model.Movie;

import java.util.List;

public interface IMovieService {

    Movie add(Movie movie);

    Movie update(Movie movie);

    boolean delete(int id);

    Movie getById(int id);

    List<Movie> getAll();

    List<Movie> getByTitle(String title);

    List<Movie> getByGenre(Movie.Genre genre);

    void cancelMovie(int movieId);

    List<Movie> searchMovieByTitleAndGenre(String title, Movie.Genre genre);


}
