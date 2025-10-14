package com.example.demoproject.exceptions;

public class SilentException extends RuntimeException {
    public SilentException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public SilentException(Throwable cause) {
        super(cause);
    }
}
