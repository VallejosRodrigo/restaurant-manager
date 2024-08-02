package com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception;

public class IdCannotBeNullException extends RuntimeException {

    public final static String MESSAGE_ID_NOT_NULL = "ID cannot be null";
    public final static String MESSAGE_NAME_NOT_NULL = "ID cannot be null";

    public IdCannotBeNullException(String message){ super(message);}
    public IdCannotBeNullException(String message, Throwable cause){ super(message, cause);}
}
