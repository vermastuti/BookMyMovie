package com.example.BookMyMovie.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String invalidCredentials) {
        super(invalidCredentials);
    }
}
