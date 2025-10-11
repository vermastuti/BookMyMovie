package com.example.BookMyMovie.repository;

import com.example.BookMyMovie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    @Query("Delete from User u where u.id=:id")
//    void delete(User user);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}


