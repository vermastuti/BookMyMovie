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


    MovieShow findByShowId(Integer showId);

    MovieShow update(MovieShow movieShow);
//    void cancelShow(int showId);
//    void cancelShowsByMovieId(Integer movieId);package com.example.BookMyMovie.service;
//
//import com.example.BookMyMovie.exception.IdAlreadyExistException;
//import com.example.BookMyMovie.model.MovieShow;
//import com.example.BookMyMovie.model.MovieShowDto;
//import com.example.BookMyMovie.repository.MovieRepository;
//import com.example.BookMyMovie.repository.ShowRepository;
//import com.example.BookMyMovie.repository.TheatreRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ShowService implements IShowService{
//    @Autowired
//    ShowRepository showRepository;
//
//    @Autowired
//    MovieRepository movieRepository;
//
//    @Autowired
//    TheatreRepository theatreRepository;
//
//
//    @Override
//    public MovieShow add(MovieShowDto movieShowDto) throws IdAlreadyExistException {
//        if (showRepository.existsById(movieShowDto.getMovieId()))
//            throw new IdAlreadyExistException("Movie Id already Present");
//        if (theatreRepository.existsById(movieShowDto.getTheatreId()))
//            throw new IdAlreadyExistException("theatre Id already Present");
//        if(showRepository.existsByTheatreIdandShowTime(movieShowDto.getTheatreId(),movieShowDto.getShowTime(),movieShowDto.getShowDate()))
//            throw new IdAlreadyExistException("Show time not available");
//        else{
//            MovieShow show =new MovieShow();
//            show.setMovieId(movieShowDto.getMovieId());
//            show.setShowDate(movieShowDto.getShowDate());
//            show.setShowTime(movieShowDto.getShowTime());
//            show.setTheatreId(movieShowDto.getTheatreId());
//            return showRepository.save(show);
//        }
//    }
//
////    @Override
////    public List<MovieShow> viewAllShow() {
////        List<MovieShow> movieShows = showRepository.findAll();
////        return movieShows;
////
////    }
////
////    @Override
////    public List<MovieShow> findByMovieId(Integer movieId) {
////        return showRepository.findByMovieId(movieId);
////    }
////
////    @Override
////    public void cancelShow(int showId) {
////        Optional<MovieShow> show = showRepository.findById(showId);
////        if(show.isPresent()){
////            MovieShow cancelledShow = show.get();
////            cancelledShow.setStatus("cancelled");
////            showRepository.save(cancelledShow);
////        } else {
////            throw new IdDoesNotExistException("Show Id not found");
////        }
////    }
////
////
////    @Override
////    public void cancelShowsByMovieId(Integer movieId) {
////        List<MovieShow> shows = showRepository.findByMovieId(movieId);
////        for(MovieShow show: shows){
////            this.cancelShow(show.getShowId());
////        }
//    }
}
