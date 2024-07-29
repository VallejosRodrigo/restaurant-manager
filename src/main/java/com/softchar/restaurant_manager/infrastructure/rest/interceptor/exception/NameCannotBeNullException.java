package com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception;

public class NameCannotBeNullException extends RuntimeException{

    public NameCannotBeNullException(String message){ super(message);}
    public NameCannotBeNullException(String message, Throwable cause){ super(message, cause);}
}
