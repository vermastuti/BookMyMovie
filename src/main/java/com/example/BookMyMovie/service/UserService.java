package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.AuthResponse;
import com.example.BookMyMovie.dto.LoginRequest;
import com.example.BookMyMovie.dto.RegisterRequest;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.exception.InvalidCredentialsException;
import com.example.BookMyMovie.model.UserProfile;
import com.example.BookMyMovie.repository.UserRepository;
import com.example.BookMyMovie.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserProfile add(UserProfile userProfile) {
        boolean isPresent = userRepository.existsById(userProfile.getUserId());
        if (isPresent) {
            throw new DuplicateIdFoundException("Duplicate User found");
        } else {
            return userRepository.save(userProfile);
        }
    }

    @Override
    public void delete(UserProfile userProfile) {
        boolean isPresent = userRepository.existsById(userProfile.getUserId());
        if (isPresent) {
            userRepository.delete(userProfile);
        } else {
            throw new IdDoesNotExistException("User Id not found");
        }
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        boolean isPresent = userRepository.existsById(userProfile.getUserId());
        if (isPresent) {
            return userRepository.save(userProfile);
        } else {
            throw new IdDoesNotExistException("User Id not found");
        }
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateIdFoundException("Email already exists");
        }

        UserProfile userProfile = new UserProfile(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        userRepository.save(userProfile);

        String token = jwtUtil.generateToken(userProfile.getEmail(), userProfile.getRole());
        return new AuthResponse(token, userProfile.getEmail(), userProfile.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        UserProfile userProfile = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), userProfile.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(userProfile.getEmail(), userProfile.getRole());
        return new AuthResponse(token, userProfile.getEmail(), userProfile.getRole());
    }

}
