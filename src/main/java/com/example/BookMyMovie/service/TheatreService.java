package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.TheatreDTO;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Theatre;
import com.example.BookMyMovie.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService implements ITheatreService{

    @Autowired
    TheatreRepository theatreepository;

    @Override
    public Theatre add(TheatreDTO theatredto) {
        Optional<Theatre> isPresent = theatreepository.findByName(theatredto.getName());
        if (isPresent.isPresent()){
            throw new DuplicateIdFoundException("Duplicate Theatre Found");
        } else {
            Theatre theatre=new Theatre();
            theatre.setCity(theatredto.getCity());
            theatre.setName(theatredto.getName());
            theatre.setSeats(theatredto.getSeats());
            Theatre added =theatreepository.save(theatre);
            return added;
        }
    }
    @Override
    public List<Theatre> getAll() {
        return theatreepository.findAll();
    }

    @Override
    public Theatre updateById(Integer theatreid,TheatreDTO theatredto) {
        Optional<Theatre> isPresent = theatreepository.findById(theatreid);
        if (isPresent.isEmpty()){
            throw new IdDoesNotExistException("Theatre Not Found");
        } else {
            Theatre theatre=isPresent.get();
            theatre.setCity(theatredto.getCity());
            theatre.setName(theatredto.getName());
            theatre.setSeats(theatredto.getSeats());
            Theatre added =theatreepository.save(theatre);
            return added;
        }
    }

}
