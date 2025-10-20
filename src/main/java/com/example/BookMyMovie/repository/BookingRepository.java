package com.example.BookMyMovie.repository;

import com.example.BookMyMovie.model.Booking;
import com.example.BookMyMovie.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findByUserProfileId(int userProfileId);

    boolean existsByUserProfileIdAndMovieShowIdAndStatusNot(
            Integer userProfileId,
            Integer movieShowId,
            Booking.Status status
    );
}
