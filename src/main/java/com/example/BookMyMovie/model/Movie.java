package com.example.BookMyMovie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public enum Genre {
        ROMANTIC,
        ACTION,
        COMEDY,
        DRAMA,
        THRILLER,
        TRAVEL
    }

    @Id
    @JsonProperty("id")
    private int movieId;

//    @NotBlank(message = "Movie title is required")
    private String title;


    @NotNull(message = "Genre can not be null")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Language mlanguage;

    private LocalDate releaseDate;


    private LocalTime duration;

//    @ElementCollection
//    private Collection<String> movieCast;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating cannot be more than 10")
    private Integer rating;

    @NotBlank(message = "Status is Required")
    @Column(nullable = false)
    String status="UPCOMING";

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

//    @OneToMany(mappedBy = "movie")
//    private Collection<Show> shows;

    public Movie() {
    }

    public Movie(int movieId, String status, String title, Genre genre, Language language, LocalDate releaseDate, LocalTime duration, Integer rating) {
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
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

    @AssertTrue(message = "Duration must be between 20 minutes and 3 hours")
    public boolean isDurationValid() {
        if (duration == null) return false;

        int totalMinutes = duration.getHour() * 60 + duration.getMinute();

        return totalMinutes >= 20 && totalMinutes <= 180;
    }
}
