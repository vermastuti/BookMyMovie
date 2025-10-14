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
@EntityListeners(AuditingEntityListener.class)
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
    private Integer movieId;

    @NotBlank(message = "Movie title is required")
    private String title;
    private String genre;

    @Enumerated(EnumType.STRING)
    private Language language;
    private LocalDate releaseDate;
    private LocalTime duration;
    private Collection<String> movieCast;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating cannot be more than 10")
    private Integer rating;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

//    @OneToMany(mappedBy = "movie")
//    private Collection<Show> shows;

    public Movie() {
    }

    public Movie(int movieId, String title, String genre, Language language, LocalDate releaseDate, LocalTime duration, Collection<String> movieCast, Integer rating) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.movieCast = movieCast;
        this.rating = rating;
    }

//    public Collection<Show> getShows() {
//        return shows;
//    }
//
//    public void setShows(Collection<Show> show) {
//        this.shows = show;
//    }

    public int getMovieId() {
        return movieId;
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

    public Collection<String> getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(Collection<String> cast) {
        this.movieCast = cast;
    }

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
                ", language=" + language +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
//                ", movieCast=" + movieCast +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
