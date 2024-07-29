package com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){ super(message);}
    public ResourceNotFoundException(String message, Throwable cause){ super(message, cause);}
}
