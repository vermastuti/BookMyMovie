package com.example.BookMyMovie.dto;

import com.example.BookMyMovie.model.UserProfile;

public class RegisterRequest {
    private String email;
    private String password;
    private UserProfile.Role role;

    public RegisterRequest() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile.Role getRole() { return role;}

    public void setRole(UserProfile.Role role) { this.role = role; }
}

