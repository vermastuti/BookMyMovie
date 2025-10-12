package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.ShowIdAlreadyExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.Show;
import com.example.BookMyMovie.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShowService implements IShowService{
    @Autowired
    ShowRepository showRepository;

    @Override
    public Show add(Show show) throws ShowIdAlreadyExistException {
        boolean isExist=  showRepository.existsById(show.getShowId());
        if (isExist)
            throw new ShowIdAlreadyExistException("Duplicate Id");
        else {
            Show newShow = showRepository.save(show);
            return newShow;
        }

    }

    @Override
    public List<Show> viewAllShow() {
        List<Show> shows=   showRepository.findAll();
        return shows;

    }

}
