package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice  // all controllers exceptions to handle
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    // This method handles the case where a requested resource (like an employee) is not found.
    public ResponseEntity<ApiError> handleResourceNotFound(NoSuchElementException exception) {

        // Build an ApiError object with the status of 404 (Not Found) and a message explaining the error
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)  // Set the HTTP status to 404
                .message("Resource not Found")  // Set the error message
                .build();  // Build the final ApiError object

        // Return a ResponseEntity with the ApiError object and the HTTP status code of 404
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}

