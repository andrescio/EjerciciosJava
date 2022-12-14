package com.example.block7crudvalidation.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

// Error para cuando no existan registros con la id que se especifica en la petición
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private CustomError customError;
    public EntityNotFoundException(){
        Date date = new Date();
        customError = new CustomError(date, 404, "Registro no existente");
    }

    public EntityNotFoundException(String message){
        Date date = new Date();
        customError = new CustomError(date, 404, message);
    }

    public CustomError getCustomError() {
        return customError;
    }
}
