package com.example.BookMyMovie.service;
import com.example.BookMyMovie.dto.MovieShowDto;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.exception.ShowCanNotBeCancelled;
import com.example.BookMyMovie.model.MovieShow;
import com.example.BookMyMovie.repository.MovieRepository;
import com.example.BookMyMovie.repository.ShowRepository;
import com.example.BookMyMovie.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService implements IShowService {

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
        if (showRepository.findByTheatreIdAndShowDateAndShowTime(movieShowDto.getTheatreId()
                , movieShowDto.getShowDate()
                , movieShowDto.getShowTime()).isPresent()) {
            throw new DuplicateIdFoundException("Show time not available");
        } else {
            MovieShow show = new MovieShow();
            show.setMovieId(movieShowDto.getMovieId());
            show.setShowDate(movieShowDto.getShowDate());
            show.setShowTime(movieShowDto.getShowTime());
            show.setTheatreId(movieShowDto.getTheatreId());
            show.setTotalPrice(movieShowDto.getTotalPrice());
            show.setSeats(movieShowDto.getSeats());
            show.setAvailableSeats(show.getSeats());
            return showRepository.save(show);
        }
    }

    @Override
    public List<MovieShow> viewAllShow() {
        List<MovieShow> movieShows = showRepository.findAll();
        return movieShows;

    }

    @Override
    public List<MovieShow> findByMovieId(Integer movieId) {
        List<MovieShow> shows = showRepository.findBymovieId(movieId);

        if (shows == null || shows.isEmpty()) {
            throw new IdDoesNotExistException("No shows found for movie ID: " + movieId);
        }

        return shows;
    }

    @Override
    public MovieShow findByShowId(Integer showId){
        Optional<MovieShow> movieShow = showRepository.findById(showId);

        if(movieShow.isPresent()){
            return movieShow.get();
        } else {
            throw new IdDoesNotExistException("No shows found for ID: " + showId);
        }
    }


    @Override
    public void reduceSeatsByshowId(Integer showId,Integer seats) {
        Optional<MovieShow> showOpt = showRepository.findById(showId);

        if (showOpt.isPresent()) {
            MovieShow show = showOpt.get();
            int oldSeats = show.getAvailableSeats();
            int newSeats = oldSeats - seats;
            show.setAvailableSeats(newSeats);

            showRepository.save(show);  // Save the updated record

            System.out.println("Seats updated successfully. Old: " + oldSeats + " → New: " + newSeats);
        } else {
            throw new IdDoesNotExistException("Show Id not found");
        }
    }


    @Override
    public void increseSeatByshowId(Integer showId, Integer seats) {
        Optional<MovieShow> showOpt = showRepository.findById(showId);

        if (showOpt.isPresent()) {
            MovieShow show = showOpt.get();
            int oldSeats = show.getAvailableSeats();
            int newSeats = oldSeats + seats;
            show.setAvailableSeats(newSeats);

            showRepository.save(show);  // Save the updated record

            System.out.println("Seats updated successfully. Old: " + oldSeats + " → New: " + newSeats);
        } else {
            throw new IdDoesNotExistException("Show Id not found");
        }
    }

    @Override
    public void cancelShowByShowId(int showId) throws ShowCanNotBeCancelled {
        Optional<MovieShow> shows = showRepository.findById(showId);
        MovieShow show = shows.get();
        if (shows.isPresent()) {
            if(show.getAvailableSeats()==0 && show.getStatus()== MovieShow.ShowStatus.Available)
            {
                throw new ShowCanNotBeCancelled("This show is Full can't cancelled now");
            }
            else {
                show.setStatus(MovieShow.ShowStatus.Available);
            }
        }
        else{
            throw new IdDoesNotExistException("Show Id not found");
        }
    }

    @Override
    public void cancelShowsByMovieId(Integer movieId) throws ShowCanNotBeCancelled {
        List<MovieShow> shows = showRepository.findBymovieId(movieId);
        if (shows.isEmpty()) {
            throw new IdDoesNotExistException("Show Id not found");
        }
        for(MovieShow show: shows){
            if(show.getAvailableSeats()==0 && show.getStatus()== MovieShow.ShowStatus.Available)
            {
                throw new ShowCanNotBeCancelled("This show is Full can't cancelled now");
            }
            else {
                show.setStatus(MovieShow.ShowStatus.Available);
            }
        }
    }

    @Override
    public MovieShow update(MovieShow movieShow) {
        boolean isPresent = showRepository.existsById(movieShow.getShowId());
        if (isPresent) {
            return showRepository.save(movieShow);
        } else {
            throw new IdDoesNotExistException("Show Id Not Found");
        }

    }

}

