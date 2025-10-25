package com.example.BookMyMovie.repository;

import  com.example.BookMyMovie.model.MovieShow;
import com.example.BookMyMovie.model.MovieShow;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<MovieShow,Integer> {

    Optional<MovieShow> findByMovieId(Integer movieId);

    Optional<MovieShow> findByTheatreIdAndShowDateAndShowTime(Integer theatreId, LocalDate showDate, LocalTime showTime);
}

