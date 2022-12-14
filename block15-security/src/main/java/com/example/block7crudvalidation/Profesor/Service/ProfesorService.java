package com.example.block7crudvalidation.Profesor.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Profesor.Model.Profesor;

import java.util.List;

// Interface con los métodos que tiene que implementar el ProfesorServiceImpl
public interface ProfesorService {
    Profesor addProfesor(Profesor profesor) throws EntityNotFoundException, UnprocessableEntityException;
    Profesor updateProfesor(Profesor profesor) throws EntityNotFoundException, UnprocessableEntityException;
    void deleteProfesor(int id) throws Exception;
    String getProfesorById(int id, String outputType) throws Exception;
    List<String> findAllProfesors(String outputType);
}
