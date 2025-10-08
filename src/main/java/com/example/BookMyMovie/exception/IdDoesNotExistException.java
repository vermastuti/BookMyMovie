package com.example.BookMyMovie.exception;

public class IdDoesNotExistException extends RuntimeException {
    public IdDoesNotExistException(String message) {
        super(message);
    }
}
