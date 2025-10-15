package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.ShowIdAlreadyExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.MovieShow;
import com.example.BookMyMovie.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShowService implements IShowService{
    @Autowired
    ShowRepository showRepository;

    @Override
    public MovieShow add(MovieShow movieShow) throws ShowIdAlreadyExistException {
        boolean isExist =  showRepository.existsById(movieShow.getShowId());
        if (isExist)
            throw new ShowIdAlreadyExistException("Duplicate Id");
        else {
            MovieShow newMovieShow = showRepository.save(movieShow);
            return newMovieShow;
        }

    }

    @Override
    public List<MovieShow> viewAllShow() {
        List<MovieShow> movieShows = showRepository.findAll();
        return movieShows;

    }

    @Override
    public List<MovieShow> findByMovie(Movie movie) {
        return showRepository.findByMovie(movie);
    }

}
