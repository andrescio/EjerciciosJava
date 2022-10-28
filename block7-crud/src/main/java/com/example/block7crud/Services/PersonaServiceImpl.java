package com.example.block7crud.Services;

import com.example.block7crud.Interfaces.PersonaRepository;
import com.example.block7crud.Interfaces.PersonaService;
import com.example.block7crud.Model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Clase que actúa como servicio de la clase persona. Implementa los métodos necesarios para efectuar los CRUD
@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona addPersona(Persona persona) {
        personaRepository.save(persona);
        return persona;
    }

    @Override
    public Persona updatePersona(Persona persona) throws Exception {
        Optional<Persona> personaAModificar = personaRepository.findById(persona.getId());
        if(personaAModificar.isEmpty() == true){
            throw new Exception("No se puede actualizar una persona que no existe. Verifique el Id");
        }
        personaRepository.save(persona);
        return persona;
    }

    @Override
    public void deletePersona(int id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Optional<Persona> getPersonaById(int id){
        return personaRepository.findById(id);
    }

   @Override
    public List<Persona> findByName(String name){
        return personaRepository.findByName(name);
   }
}
