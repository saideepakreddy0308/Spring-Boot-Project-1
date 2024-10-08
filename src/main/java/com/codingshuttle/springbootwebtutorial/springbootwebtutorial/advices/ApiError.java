package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data  // so we can use getters and setters everywhere
@Builder // so we can create easily
public class ApiError {

    private HttpStatus status;

    private String message;
}


