package com.example.todo.Exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        //1. Create payload containing exception details
        ApiException apiException = new ApiException(
                e.getMessage(),e, badRequest, ZonedDateTime.now(ZoneId.of("Z")
        ));
        //2. Return ResponseEntity
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleUnauthorizedRequestException(IllegalArgumentException e){
        HttpStatus unauthorized = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(e.getMessage(),e, unauthorized, ZonedDateTime.now(ZoneId.of("Z")));
        return  new ResponseEntity<>(apiException,unauthorized);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleNotUniqueValueException(DataIntegrityViolationException e){
        HttpStatus unauthorized = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(e.getMessage(),e, unauthorized, ZonedDateTime.now(ZoneId.of("Z")));
        return  new ResponseEntity<>(apiException,unauthorized);
    }
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleCannotFindUserByPrincipal(NullPointerException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        System.out.println("какашки");
        ApiException apiException = new ApiException(e.getMessage(),e,status,ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException,status);
    }



}
