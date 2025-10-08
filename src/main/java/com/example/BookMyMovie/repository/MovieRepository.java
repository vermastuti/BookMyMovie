package com.example.BookMyMovie.repository;

import com.example.BookMyMovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie getByTitle(String title);

    @Query("select m from Movie m where m.title like concat('%', ?1, '%')")
    List<Movie> getMoviesByTitle(String title);
}
