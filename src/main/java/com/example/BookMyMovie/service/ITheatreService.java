package com.example.BookMyMovie.service;


import com.example.BookMyMovie.dto.TheatreDTO;
import com.example.BookMyMovie.model.Theatre;

import java.util.List;

public interface ITheatreService {

    Theatre add(TheatreDTO theatredto);
    List<Theatre> getAll();
    Theatre updateById(Integer theatreid,TheatreDTO theatredto);
    }



