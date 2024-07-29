package com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception;

public class IdCannotBeNullException extends RuntimeException {

    public IdCannotBeNullException(String message){ super(message);}
    public IdCannotBeNullException(String message, Throwable cause){ super(message, cause);}
}
