package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.dto.AuthenticationRequest;
import com.example.BookMyMovie.dto.AuthenticationResponse;
import com.example.BookMyMovie.dto.RegisterRequest;
import com.example.BookMyMovie.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth-service")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

//    @Autowired
//    public AuthenticationController(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
//        try {

            AuthenticationResponse res = authenticationService.register(registerRequest);
            return ResponseEntity.ok(res);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse res = authenticationService.authenticate(authenticationRequest);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestParam("token") String refreshToken) {
        try {
            AuthenticationResponse res = authenticationService.refreshToken(refreshToken);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestParam("token") String token) {
        try {
            Boolean res = authenticationService.validateToken(token);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}