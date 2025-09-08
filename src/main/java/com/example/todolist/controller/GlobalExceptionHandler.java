package com.example.todolist.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static class ErrorResponse {
        private final LocalDateTime timestamp;
        private final String message;
        private final String path;

        public ErrorResponse(String message, String path) {
            this.timestamp = LocalDateTime.now();
            this.message = message;
            this.path = path;
        }

        // Getters for serialization
        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }
    }


}
