package com.example.block7crudvalidation.Profesor.Infraestructure.Repository;

import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// Interface con los métodos CRUD por defecto de CrudRepository.
public interface ProfesorRepository extends CrudRepository<Profesor, Integer> {
    Optional<Profesor> findByPersona(Persona persona);
}
