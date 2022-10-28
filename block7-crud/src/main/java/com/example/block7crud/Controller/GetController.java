package com.example.block7crud.Controller;

import com.example.block7crud.Model.Persona;
import com.example.block7crud.Services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

// Controlador que recibe una petición GET y busca a la Persona tanto por id como por nombre
@RestController
@RequestMapping("/persona")
public class GetController {
    @Autowired
    private PersonaServiceImpl personaServiceImpl;

    @GetMapping("/{id}")
    public String getPersonaById(@PathVariable int id){
       Optional<Persona> persona = personaServiceImpl.getPersonaById(id);
       if(persona.isEmpty() == true){
           return "No existe una persona con ese Id";
       }
       return persona.get().toString();
    }

    @GetMapping("/nombre/{name}")
    public String getPersonaByName(@PathVariable String name){
        List<Persona> personas= personaServiceImpl.findByName(name);
        if(personas.size() == 0){
            return "No existen personas con ese nombre";
        }
        return personas.toString();
    }
}
