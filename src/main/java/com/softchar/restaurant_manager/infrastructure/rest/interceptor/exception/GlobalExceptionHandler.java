package com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    MessageException handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException e){
        return MessageException.builder()
                .message(e.getLocalizedMessage())
                .uri(request.getRequestURI())
                .build();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdCannotBeNullException.class)
    MessageException handleIdCannotBeNullException(HttpServletRequest request, IdCannotBeNullException e){
        return MessageException.builder()
                .message(e.getLocalizedMessage())
                .uri(request.getRequestURI())
                .build();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NameCannotBeNullException.class)
    MessageException handleNameCannotBeNullException(HttpServletRequest request, NameCannotBeNullException e){
        return MessageException.builder()
                .message(e.getLocalizedMessage())
                .uri(request.getRequestURI())
                .build();
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(TableAlreadyBookedException.class)
    MessageException handleTableAlreadyBookedException(HttpServletRequest request, TableAlreadyBookedException e){
        return MessageException.builder()
                .message(e.getLocalizedMessage())
                .uri(request.getRequestURI())
                .build();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    MessageException handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e){
        return MessageException.builder()
                .message(e.getLocalizedMessage())
                .uri(request.getRequestURI())
                .build();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    MessageException handleGenericException(HttpServletRequest request){
        return MessageException.builder()
                .message("Server error! T_T ")
                .uri(request.getRequestURI())
                .build();
    }

}
