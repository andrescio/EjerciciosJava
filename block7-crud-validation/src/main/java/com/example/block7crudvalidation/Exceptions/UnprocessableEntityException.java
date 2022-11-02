package com.example.block7crudvalidation.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

// Error para cuando se intente añadir o modificar una persona cuyos campos no cumplen lo requerido
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException{
    private CustomError customError;

    public UnprocessableEntityException(){
        Date date = new Date();
        customError = new CustomError(date, 422, "Los campos no cumplen los requisitos");
    }

    public CustomError getCustomError() {
        return customError;
    }
}
