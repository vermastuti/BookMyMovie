package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.exception.ShowIdAlreadyExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.MovieShow;
import com.example.BookMyMovie.repository.BookingRepository;
import com.example.BookMyMovie.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService implements IShowService{
    @Autowired
    ShowRepository showRepository;

    @Autowired
    BookingRepository bookingRepository;

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

    @Override
    public void cancelShow(int showId) {
        Optional<MovieShow> show = showRepository.findById(showId);
        if(show.isPresent()){
            MovieShow cancelledShow = show.get();
            cancelledShow.setStatus("cancelled");
            showRepository.save(cancelledShow);
        } else {
            throw new IdDoesNotExistException("Show Id not found");
        }
    }


    @Override
    public void cancelShowsByMovie(int movieId) {
//        List<MovieShow> shows = showRepository.findByMovieId(movieId);
//        for(MovieShow show: shows){
//            this.cancelShow(show.getShowId());
//        }
    }

}
