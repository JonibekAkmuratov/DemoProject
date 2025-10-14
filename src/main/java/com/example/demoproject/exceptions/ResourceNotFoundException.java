package com.example.demoproject.exceptions;

import static com.example.demoproject.exceptions.ErrorCode.NOT_FOUND;



public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {super(NOT_FOUND);}
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
