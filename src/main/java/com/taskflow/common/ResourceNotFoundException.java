package com.taskflow.common;

/**
 * Thrown by the service layer when a requested resource does not exist.
 * The GlobalExceptionHandler turns this into a clean 404 response.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
