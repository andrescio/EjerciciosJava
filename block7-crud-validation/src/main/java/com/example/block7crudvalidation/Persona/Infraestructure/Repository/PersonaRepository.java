package com.example.block7crudvalidation.Persona.Infraestructure.Repository;

import com.example.block7crudvalidation.Persona.Model.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// Interface con los métodos CRUD por defecto de CrudRepository más uno a mayores para buscar por usuario.
public interface PersonaRepository extends CrudRepository<Persona, String> {
    Optional<Persona> findById(String id);
    List<Persona> findByUsuario(String usuario);
}
