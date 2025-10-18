package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    //We are writing test cases on movieservice, it is never mocked
    private MovieService movieService;

    //depdencies are always mocked
    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    void setup() {
        movieService = new MovieService(movieRepository);
    }

    //Add Movie
    @Test
    void43shouldAddMovieWhenTheMovieIsNotPresentInCatalog() {
        Movie movie = new Movie();
        movie.setMovieId(1);

    //Always mock the dependency, we are mocking the syntax of below line
        // boolean isPresent = movieRepository.existsById(movie.getMovieId());
        when(movieRepository.existsById(1)).thenReturn(false);
        //Actual method called
        movieService.add(movie);
        //verify
        //movieRepository.save(movie);

       verify(movieRepository, times(1)).save(movie);
    }

    @Test
    void shouldDisallowAddingMovieWhenMovieIsAlreadyPresentInCatalog() {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setTitle("Ye jawani hai deewani");
        //setup
        when(movieRepository.existsById(1)).thenReturn(true);
        //expect
        assertThrows(DuplicateIdFoundException.class ,() ->  movieService.add(movie));
    }

//Updating the movie
    @Test
    void shouldUpdateTheMovieWhenIdIsPresent(){
        Movie movie=new Movie();
        movie.setMovieId(1);

        //Mocking the dependency and converting this code
        //boolean isPresent = movieRepository.existsById(movie.getMovieId());
        when(movieRepository.existsById(1)).thenReturn(true);
       //calling method
        movieService.update(movie);
        // verify(movieRepository, times(1)).save(movie);
        verify(movieRepository,times(1)).save(movie);
    }

    @Test
    void ShouldNotAllowUpdateIfIdIsNotPresent()
    {
        Movie movie=new Movie();
        movie.setMovieId(1);
        assertThrows(IdDoesNotExistException.class,()->movieService.update(movie));
    }


    @Test
    void ShouldReturnTheMovieIfMovieIdIsPresent()
    {
        Movie movie=new Movie();
        movie.setMovieId(1);
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        movieService.getById(1);

        verify(movieRepository,times(1)).findById(1);
    }

    @Test
    void ShouldNotGetTheMovieIfMovieIdIsNotPresent()
    {
        Movie movie=new Movie();
        movie.setMovieId(1);

        assertThrows(IdDoesNotExistException.class,()->movieService.getById(1));
    }


}