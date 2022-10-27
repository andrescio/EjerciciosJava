package com.example.block7crud.Interfaces;

import com.example.block7crud.Model.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {
    List<Persona> findByName(String name);
}
