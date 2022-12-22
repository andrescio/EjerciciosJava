package com.example.block16springcloud.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


// Class that handles different Exceptions
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Exception response when an UnprocessableEntityException is thrown
    @ExceptionHandler(value = {UnprocessableEntityException.class})
    public ResponseEntity<Object> handleUnprocessableEntityExceptions(Exception exception, WebRequest webRequest) {
        HttpStatus errorCode = HttpStatus.UNPROCESSABLE_ENTITY;

        return this.handleExceptionInternal(exception,
                new CustomError(new Date(),
                        errorCode.value(),
                        exception.getMessage()),
                new HttpHeaders(),
                errorCode,
                webRequest);
    }

    // Exception response when an EntityNotFoundException is thrown
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundExceptions(Exception exception, WebRequest webRequest) {
        HttpStatus errorCode = HttpStatus.NOT_FOUND;

        return this.handleExceptionInternal(exception,
                new CustomError(new Date(),
                        errorCode.value(),
                        exception.getMessage()),
                new HttpHeaders(),
                errorCode,
                webRequest);
    }

    // Exception response when a FeignException is thrown
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignStatusException(FeignException exception, WebRequest webRequest) {
        HttpStatus errorCode = HttpStatus.CONFLICT;
        return this.handleExceptionInternal(exception,
                new CustomError(new Date(),
                        errorCode.value(),
                        "Passenger already has a ticket for this trip"),
                new HttpHeaders(),
                errorCode,
                webRequest);
    }
}
