package com.example.BookMyMovie.repository;

import com.example.BookMyMovie.model.Movie;
import com.example.BookMyMovie.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer>
{
}

