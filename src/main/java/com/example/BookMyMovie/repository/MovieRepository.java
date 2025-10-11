package com.example.BookMyMovie.repository;

import com.example.BookMyMovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(String genre);
}
