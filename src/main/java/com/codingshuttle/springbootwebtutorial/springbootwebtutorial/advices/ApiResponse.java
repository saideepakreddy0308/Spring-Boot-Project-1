package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import lombok.Data;

import java.time.LocalDateTime;

// lets handle, instead of lombok
// making it generic Type

@Data
public class ApiResponse<T> {

    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();  // calling default constructor
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}
