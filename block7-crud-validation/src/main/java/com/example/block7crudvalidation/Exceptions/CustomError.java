package com.example.block7crudvalidation.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// Clase de un objeto que representa un error personalizado.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomError {
    Date timestamp;
    int HttpCode;
    String message;
}
