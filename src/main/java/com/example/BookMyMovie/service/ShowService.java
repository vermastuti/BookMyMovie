package com.example.BookMyMovie.service;
import com.example.BookMyMovie.dto.MovieShowDto;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.MovieShow;
import com.example.BookMyMovie.repository.MovieRepository;
import com.example.BookMyMovie.repository.ShowRepository;
import com.example.BookMyMovie.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService implements IShowService{

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;


    @Override
    public MovieShow add(MovieShowDto movieShowDto) throws DuplicateIdFoundException {
        if (!movieRepository.existsById(movieShowDto.getMovieId()))
            throw new IdDoesNotExistException("Movie Not Present");
        if (!theatreRepository.existsById(movieShowDto.getTheatreId()))
            throw new IdDoesNotExistException("theatre Not Present");
        if(showRepository.findByTheatreIdAndShowDateAndShowTime(movieShowDto.getTheatreId()
                ,movieShowDto.getShowDate()
        ,movieShowDto.getShowTime()).isPresent()){
            throw new DuplicateIdFoundException("Show time not available");}
        else{
            MovieShow show =new MovieShow();
            show.setMovieId(movieShowDto.getMovieId());
            show.setShowDate(movieShowDto.getShowDate());
            show.setShowTime(movieShowDto.getShowTime());
            show.setTheatreId(movieShowDto.getTheatreId());
            return showRepository.save(show);
        }
    }

    @Override
    public List<MovieShow> viewAllShow() {
        List<MovieShow> movieShows = showRepository.findAll();
        return movieShows;

    }
//
    @Override
    public List<MovieShow> findByMovieId(Integer movieId) {
        if(showRepository.findByMovieId(movieId).isEmpty()){
            throw new IdDoesNotExistException("Id Not Present");
        }
        return showRepository.findByMovieId(movieId).stream().toList();
    }

//    @Override
//    public void cancelShow(int showId) {
//        Optional<MovieShow> show = showRepository.findById(showId);
//        if(show.isPresent()){
//            MovieShow cancelledShow = show.get();
//            cancelledShow.setStatus("cancelled");
//            showRepository.save(cancelledShow);
//        } else {
//            throw new IdDoesNotExistException("Show Id not found");
//        }
//    }
//
//
//    @Override
//    public void cancelShowsByMovieId(Integer movieId) {
//        List<MovieShow> shows = showRepository.findByMovieId(movieId);
//        for(MovieShow show: shows){
//            this.cancelShow(show.getShowId());
//        }
    }

