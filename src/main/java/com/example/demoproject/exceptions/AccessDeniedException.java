package com.example.demoproject.exceptions;

/**
 * @author : Elmurodov Javohir
 * @since : 05/06/23 / 17:00
 */

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

}
