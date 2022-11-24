package com.example.block11uploaddownloadfiles.Exception;

import com.sun.jdi.InvalidTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Date;

// Class that handles different Exceptions

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Exception response when a FileAlreadyExistsException is thrown
    @ExceptionHandler(value = {FileAlreadyExistsException.class})
    public ResponseEntity<Object> handleFileAlreadyExistsExceptions(Exception exception, WebRequest webRequest) {
        HttpStatus errorCode = HttpStatus.CONFLICT;

        return this.handleExceptionInternal(exception,
                                           new CustomError(new Date(),
                                                           errorCode.value(),
                                                           exception.getMessage()),
                                           new HttpHeaders(),
                                           errorCode,
                                           webRequest);
    }

    // Exception response when a InvalidTypeException is thrown
    @ExceptionHandler(value = {InvalidTypeException.class})
    public ResponseEntity<Object> handleInvalidTypeExceptions(Exception exception, WebRequest webRequest) {
        HttpStatus errorCode = HttpStatus.FORBIDDEN;

        return this.handleExceptionInternal(exception,
                                            new CustomError(new Date(),
                                                            errorCode.value(),
                                                            exception.getMessage()),
                                            new HttpHeaders(),
                                            errorCode,
                                            webRequest);
    }

    // Exception response when a FileNotFoundException is thrown
    @ExceptionHandler(value = {FileNotFoundException.class})
    public ResponseEntity<Object> handleFileNotFoundExceptions(Exception exception, WebRequest webRequest) {
        HttpStatus errorCode = HttpStatus.NOT_FOUND;

        return this.handleExceptionInternal(exception,
                                            new CustomError(new Date(),
                                                            errorCode.value(),
                                                            exception.getMessage()),
                                            new HttpHeaders(),
                                            errorCode,
                                            webRequest);
    }
}
