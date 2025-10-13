package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.dto.AuthResponse;
import com.example.BookMyMovie.dto.LoginRequest;
import com.example.BookMyMovie.dto.RegisterRequest;
import com.example.BookMyMovie.model.UserProfile;
import com.example.BookMyMovie.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody UserProfile userProfile) {
        userService.delete(userProfile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UserProfile userProfile) {
        UserProfile updatedUserProfile = userService.update(userProfile);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }

}
