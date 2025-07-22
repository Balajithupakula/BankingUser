package com.example.user;
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}