package com.xyz.moviebooking.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SeatUnavailableException.class)
    public ResponseEntity<Map<String, Object>> handleSeatUnavailable(SeatUnavailableException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)   // 409 is correct for booking conflict
                .body(Map.of(
                        "timestamp", Instant.now().toString(),
                        "status", 409,
                        "error", "Seat Unavailable",
                        "message", ex.getMessage()
                ));
    }
}

