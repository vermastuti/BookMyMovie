package com.example.BookMyMovie.repository;

import com.example.BookMyMovie.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserProfile, Integer> {
//    @Query("Delete from User u where u.id=:id")
//    void delete(User user);

    boolean existsByEmail(String email);

    Optional<UserProfile> findByEmail(String email);

    Optional<UserProfile> findByRole(UserProfile.Role role);
}


