package com.example.BookMyMovie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
    private String refreshToken;

    public AuthenticationResponse(String authenticationToken, String refreshToken) {
        this.authenticationToken = authenticationToken;
        this.refreshToken = refreshToken;
    }
}