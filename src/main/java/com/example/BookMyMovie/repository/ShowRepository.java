package com.example.BookMyMovie.repository;

import com.example.BookMyMovie.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<MovieShow,Integer>
{
}

