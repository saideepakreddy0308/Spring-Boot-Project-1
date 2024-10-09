package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice  // all controllers exceptions to handle
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    // This method handles the case where a requested resource (like an employee) is not found.
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception) {

        // Build an ApiError object with the status of 404 (Not Found) and a message explaining the error
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)  // Set the HTTP status to 404
                .message(exception.getMessage())  // Set the error message
                .build();  // Build the final ApiError object

        // Return a ResponseEntity with the ApiError object and the HTTP status code of 404
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class) // parent exception class
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // we need clean messages so the reader easily understands the error, so define individually
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Here it combines all the exceptions we got, and bind it together
    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException exception){
        List<String> errors = exception
                    .getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(error->error.getDefaultMessage())
                    .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
//                .message(errors.toString())
                .message(("Input Validation failed"))
                .subErrors(errors)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        // now we get concise errors as defined in the EmployeeDTO class or EmployeeRoleValidation , all of @Valid
        // also can change format of ApiError
    }
}

