package com.example.user;
public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}