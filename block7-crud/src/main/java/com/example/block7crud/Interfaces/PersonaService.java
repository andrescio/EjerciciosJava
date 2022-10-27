package com.example.block7crud.Interfaces;

import com.example.block7crud.Model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    Persona addPersona(Persona persona);
    Persona updatePersona(Persona persona) throws Exception;
    void deletePersona(int id);
    Optional<Persona> getPersonaById(int id);
    List<Persona> findByName(String name);
}
