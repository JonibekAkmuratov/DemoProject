package com.example.demoproject.exceptions;

public class CamsClientException extends RuntimeException {
    public CamsClientException(String message) {
        super(message);
    }

    public CamsClientException(Throwable cause) {
        super(cause);
    }
}
