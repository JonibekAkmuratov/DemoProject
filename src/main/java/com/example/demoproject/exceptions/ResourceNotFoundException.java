package com.example.demoproject.exceptions;

import static com.example.demoproject.exceptions.ErrorCode.NOT_FOUND;

/**
 * @author : Elmurodov Javohir
 * @since : 02/06/23 / 18:01
 */

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {super(NOT_FOUND);}
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
