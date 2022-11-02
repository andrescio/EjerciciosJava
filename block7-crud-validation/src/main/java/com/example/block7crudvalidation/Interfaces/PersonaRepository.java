package com.example.block7crudvalidation.Interfaces;

import com.example.block7crudvalidation.Models.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Interface con los métodos CRUD por defecto de CrudRepository más uno a mayores para buscar por usuario.
public interface PersonaRepository extends CrudRepository<Persona, Integer> {
    List<Persona> findByUsuario(String usuario);
}
