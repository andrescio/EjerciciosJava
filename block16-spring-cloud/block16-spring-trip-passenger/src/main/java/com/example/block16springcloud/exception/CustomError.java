package com.example.block16springcloud.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// Class that creates a CustomError that is instanced in the GlobalExceptionHandler class
// when an Exception is thrown.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomError {
    Date timestamp;

    int HttpCode;

    String message;
}
