package com.example.BookMyMovie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Entity
//@EntityListeners(AuditingEntityListener.class)
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
    private int movieId;

//    @NotBlank(message = "Movie title is required")
    private String title;
    private String genre;

    @Enumerated(EnumType.STRING)
    private Language mlanguage;
    private LocalDate releaseDate;
    private LocalTime duration;

//    @ElementCollection
//    private Collection<String> movieCast;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating cannot be more than 10")
    private Integer rating;

    String status;
    private boolean isAdmin;

//    @CreatedDate
//    private Instant createdAt;
//
//    @LastModifiedDate
//    private Instant updatedAt;

//    @OneToMany(mappedBy = "movie")
//    private Collection<Show> shows;

    public Movie() {
    }

    public Movie(boolean isAdmin, int movieId, String status, String title, String genre, Language language, LocalDate releaseDate, LocalTime duration, Integer rating) {
        this.isAdmin = isAdmin;
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.mlanguage = language;
        this.releaseDate = releaseDate;
        this.duration = duration;
//        this.movieCast = movieCast;
        this.rating = rating;
        this.status = status;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Language getMlanguage() {
        return mlanguage;
    }

    public void setMlanguage(Language mlanguage) {
        this.mlanguage = mlanguage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) { this.movieId = movieId;}

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

//    public Collection<String> getMovieCast() {
//        return movieCast;
//    }
//
//    public void setMovieCast(Collection<String> cast) {
//        this.movieCast = cast;
//    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + movieId +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", language=" + mlanguage +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
//                ", movieCast=" + movieCast +
                ", rating=" + rating +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
                '}';
    }

}
