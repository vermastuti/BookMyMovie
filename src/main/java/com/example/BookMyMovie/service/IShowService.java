package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.ShowIdAlreadyExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.Show;

import java.util.List;

public interface IShowService {
    Show add(Show show) throws ShowIdAlreadyExistException;
    List<Show> viewAllShow();

}
