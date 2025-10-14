package com.example.demoproject.exceptions;

/**
 * @author : Elmurodov Javohir
 * @since : 02/06/23 / 15:51
 */

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
