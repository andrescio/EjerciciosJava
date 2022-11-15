package com.example.block7crudvalidation.Persona.Service;

import com.example.block7crudvalidation.Persona.Model.Persona;

import java.util.List;

// Interface con los métodos que tiene que implementar el PersonaServiceImpl
public interface PersonaService {
    Persona addPersona(Persona persona) throws Exception;
    Persona updatePersona(Persona persona) throws Exception;
    void deletePersona(String id) throws Exception;
    String getPersonaById(String id, String outputType) throws Exception;
    List<String> findByUsuario(String usuario, String outputType);
    List<String> findAllPersonas(String outputType);
}
