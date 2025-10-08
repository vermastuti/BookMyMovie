package com.example.BookMyMovie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="movies")
public class Movie {

    public enum Language {
        HINDI,
        ENGLISH,
        TAMIL,
        TELUGU,
        MARATHI,
        MALAYALAM,
        BENGALI,
        BHOJPURI
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=1, message = "Min size is 1")
    private String title;
    private String genre;
    private Language language;
    private LocalDate releaseDate;
    private LocalTime duration;
    private List<String> cast;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
//    @CreatedBy
//    private

    public Movie(int id, String title, String genre, Language language, LocalDate releaseDate, LocalTime duration, List<String> cast) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.cast = cast;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }
}
