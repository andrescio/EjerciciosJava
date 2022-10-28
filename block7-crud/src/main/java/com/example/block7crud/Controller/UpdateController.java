package com.example.block7crud.Controller;

import com.example.block7crud.Model.Persona;
import com.example.block7crud.Services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controlador que recibe una petición PUT que modifica los datos de una persona ya existente
@RestController
@RequestMapping("/persona")
public class UpdateController {
    @Autowired
    private PersonaServiceImpl personaServiceImpl;

    @PutMapping
    public String updatePersona(@RequestBody Persona persona){
        if(persona.getAge() == 0 || persona.getName() == null || persona.getPoblacion() == null){
            return "No puede haber campos vacíos";
        }
        try{
            return personaServiceImpl.updatePersona(persona).toString();
        }
        catch(Exception e){
            return e.toString();
        }
    }
}
