package com.example.block7crudvalidation.Interfaces;

import com.example.block7crudvalidation.Models.Persona;
import com.example.block7crudvalidation.Models.PersonaDTO;

import java.util.List;
import java.util.Optional;

// Interface con los métodos que tiene que implementar el PersonaServiceImpl
public interface PersonaService {
    Persona addPersona(Persona persona) throws Exception;
    Persona updatePersona(Persona persona) throws Exception;
    void deletePersona(int id) throws Exception;
    PersonaDTO getPersonaById(int id) throws Exception;
    List<PersonaDTO> findByUsuario(String usuario);
    List<PersonaDTO> findAllPersonas();
}
