package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.AuthenticationRequest;
import com.example.BookMyMovie.dto.AuthenticationResponse;
import com.example.BookMyMovie.dto.RegisterRequest;
import com.example.BookMyMovie.model.UserProfile;
import com.example.BookMyMovie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

//    @Autowired
//    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        UserProfile user = new UserProfile(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getEmail(),
                UserProfile.Role.CUSTOMER,
                passwordEncoder.encode(registerRequest.getPassword())
        );
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse refreshToken(String refreshToken) {

        var user = userRepository.findByEmail(jwtService.getEmailFromToken(refreshToken)).orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));
        var jwtToken = jwtService.generateToken(user);
        var newRefreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return new AuthenticationResponse(jwtToken, newRefreshToken);
    }

    public Boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}