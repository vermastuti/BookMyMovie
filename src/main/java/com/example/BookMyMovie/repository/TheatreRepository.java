package com.example.BookMyMovie.repository;

import com.example.BookMyMovie.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Integer> {

     Optional<Theatre> findByName(String name);
}

