package com.example.block7crud.Controller;

import com.example.block7crud.Model.Persona;
import com.example.block7crud.Services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Controlador que recibe una petición POST para añadir a una Persona
@RestController
@RequestMapping("/persona")
public class addController {
    @Autowired
    private PersonaServiceImpl personaServiceImpl;

    @PostMapping
    public Persona addPersona(@RequestBody Persona persona){
        return personaServiceImpl.addPersona(persona);
    }
}
