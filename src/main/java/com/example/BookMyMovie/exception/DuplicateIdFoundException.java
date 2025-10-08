package com.example.BookMyMovie.exception;

public class DuplicateIdFoundException extends RuntimeException {
    public DuplicateIdFoundException(String message) {
        super(message);
    }
}
