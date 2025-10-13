package com.example.BookMyMovie.dto;

import com.example.BookMyMovie.model.UserProfile;

public class AuthResponse {
    private String token;
    private String email;
    private UserProfile.Role role;

    public AuthResponse(String token, String email, UserProfile.Role role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfile.Role getRole() {
        return role;
    }

    public void setRole(UserProfile.Role role) {
        this.role = role;
    }
}
