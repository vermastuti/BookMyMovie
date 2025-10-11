package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.AuthResponse;
import com.example.BookMyMovie.dto.LoginRequest;
import com.example.BookMyMovie.dto.RegisterRequest;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.exception.InvalidCredentialsException;
import com.example.BookMyMovie.model.User;
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
    public User add(User user) {
        boolean isPresent = userRepository.existsById(user.getUserId());
        if (isPresent) {
            throw new DuplicateIdFoundException("Duplicate User found");
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public void delete(User user) {
        boolean isPresent = userRepository.existsById(user.getUserId());
        if (isPresent) {
            userRepository.delete(user);
        } else {
            throw new IdDoesNotExistException("User Id not found");
        }
    }

    @Override
    public User update(User user) {
        boolean isPresent = userRepository.existsById(user.getUserId());
        if (isPresent) {
            return userRepository.save(user);
        } else {
            throw new IdDoesNotExistException("User Id not found");
        }
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateIdFoundException("Email already exists");
        }

        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                User.Role.CUSTOMER
        );

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }

}
