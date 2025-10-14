package com.example.demoproject.exceptions;

/**
 * @author : Elmurodov Javohir
 * @since : 05/06/23 / 17:00
 */

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
