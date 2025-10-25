package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieService(MovieRepository movieRepository) {
    }

    @Override
    public Movie add(Movie movie) {
        boolean isPresent = movieRepository.existsById(movie.getMovieId());
        if (isPresent) {
            throw new DuplicateIdFoundException("Duplicate Movie Id Found");
        } else {
            return movieRepository.save(movie);
        }
    }


    @Override
    public Movie update(Movie movie) {
        boolean isPresent = movieRepository.existsById(movie.getMovieId());
        if (isPresent) {
            return movieRepository.save(movie);
        } else {
            throw new IdDoesNotExistException("Movie Id Not Found");
        }

    }

    @Override
    public boolean delete(int id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            throw new IdDoesNotExistException("Movie Id Not Found");
        }
    }

    @Override
    public Movie getById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new IdDoesNotExistException("Id Not Found");
        }
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> getByGenre(Movie.Genre genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public void cancelMovie(int movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()) {
            Movie cancelledMovie = movie.get();
            cancelledMovie.setStatus("cancelled");
            movieRepository.save(cancelledMovie);
        } else {
            throw new IdDoesNotExistException("Show Id not found");
        }
    }

    @Override
    public List<Movie> searchMovieByTitleAndGenre(String title, Movie.Genre genre) {
        if (title != null && genre != null) {
           return movieRepository.findByTitleAndGenre(title, genre);
        } else if (title == null && genre != null) {
           return movieRepository.findByGenre(genre);
        } else if (title != null) {
           return movieRepository.findByTitle(title);
        } else {
            throw new EntityNotFoundException("Title and genre are not there");
        }

    }

}
