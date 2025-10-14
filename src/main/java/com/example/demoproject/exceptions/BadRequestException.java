package com.example.demoproject.exceptions;

/**
 * @author : Elmurodov Javohir
 * @since : 05/06/23 / 14:21
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
