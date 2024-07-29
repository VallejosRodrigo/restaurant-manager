package com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception;

public class TableAlreadyBookedException extends RuntimeException{

    public TableAlreadyBookedException(String message) { super(message);}
    public TableAlreadyBookedException(String message, Throwable cause) { super(message, cause);}
}
