package com.example.block13mongodb.persona.service;

import com.example.block13mongodb.persona.infraestructure.dto.PersonaDTO;
import com.example.block13mongodb.persona.model.Persona;

import java.util.List;

public interface PersonaService {
    Persona addPersona(Persona persona);
    Persona updatePersona(Persona persona) throws Exception;
    PersonaDTO getPersona(String id) throws Exception;
    String deletePersona(String id);
    List<PersonaDTO> getAllPersonas(int page, int size);
    List<PersonaDTO> getPersonasByName(String name);
    List<PersonaDTO> getPersonasByUsuario(String usuario);
}
