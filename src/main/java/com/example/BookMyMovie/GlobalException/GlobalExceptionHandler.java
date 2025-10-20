package com.example.BookMyMovie.GlobalException;


import com.example.BookMyMovie.exception.InvalidBookingException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidation(MethodArgumentNotValidException exception){
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errorMap.put(fieldName, errorMessage);
                }
        );
        return errorMap;
    }

    // ✅ Handles service-layer invalid data (e.g., null userId, zero seats)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidBookingException.class)
    public Map<String, String> handleInvalidBooking(InvalidBookingException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;

    @ExceptionHandler(EntityNotFoundException.class) // Assuming a custom exception
    public ResponseEntity<String> handleResourceNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
