package com.example.block13mongodb.persona.infraestructure.controller;

import com.example.block13mongodb.persona.infraestructure.dto.PersonaDTO;
import com.example.block13mongodb.persona.model.Persona;
import com.example.block13mongodb.persona.service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    // Petition to add a Persona
    @PostMapping
    public Persona addPersona(@RequestBody Persona persona){
        return personaServiceImpl.addPersona(persona);
    }

    // Petition to get a Persona by id
    @GetMapping("/{id}")
    public PersonaDTO addPersona(@PathVariable String id) throws Exception {
        return personaServiceImpl.getPersona(id);
    }

    // Petition to get all Persona. Requires page and size params because of the pagination
    @GetMapping("/all")
    public List<PersonaDTO> getAllPersona(@RequestParam int page,
                                       @RequestParam int size){
        return personaServiceImpl.getAllPersonas(page, size);
    }

    // Petition to get Persona by name
    @GetMapping("/name/{name}")
    public List<PersonaDTO> getPersonasByName(@PathVariable String name) {
        return personaServiceImpl.getPersonasByName(name);
    }

    // Petition to get Persona by usuario
    @GetMapping("/usuario/{usuario}")
    public List<PersonaDTO> getPersonasByUsuario(@PathVariable String usuario){
        return personaServiceImpl.getPersonasByUsuario(usuario);
    }

    // Petition to update a Persona
    @PutMapping
    public Persona updatePersona(@RequestBody Persona persona) throws Exception {
        return personaServiceImpl.updatePersona(persona);
    }

    // Petition to delete a Persona
    @DeleteMapping("/{id}")
    public String deletePersona(@PathVariable String id){
        return personaServiceImpl.deletePersona(id);
    }
}
