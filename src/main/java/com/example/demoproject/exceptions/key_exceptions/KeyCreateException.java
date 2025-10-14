package com.example.demoproject.exceptions.key_exceptions;


public class KeyCreateException extends RuntimeException {
    public KeyCreateException(String message) {
        super(message);
    }
    public KeyCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
