package com.example.block7crudvalidation.Persona.Infraestructure.Controller;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Persona.Service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaServiceImpl personaServiceImpl;

    // Método que recibe mediante una petición POST una persona y la añade a la BBDD.
    @PostMapping
    public String addPersona(@RequestBody Persona persona){
        try{
            return personaServiceImpl.addPersona(persona).toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }

    // Devuelve una persona basándose en su id
    @GetMapping("/{id}")
    public String getPersonaById(@PathVariable String id,
                                 @RequestParam(required = false) String outputType)
    {
        try{
            if(outputType == null){
                outputType = "simple";
            }
            return personaServiceImpl.getPersonaById(id,outputType);
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Devuelve personas según su nombre de usuario. Se puede especificar si se desea su información
    // simple o completa (full)
    @GetMapping("/usuario/{usuario}")
    public String findPersonaByUsuario(@PathVariable String usuario,
                                       @RequestParam(required = false) String outputType){
        return personaServiceImpl.findByUsuario(usuario,outputType).toString();
    }

    // Devuelve la lista de todas las personas
    @GetMapping("/all")
    public String findAllPersonas(@RequestParam(required = false) String outputType){
        return personaServiceImpl.findAllPersonas(outputType).toString();
    }

    // Método que recibe los datos de una persona existente y los cambia mediante
    // el método updatePersona de personaServiceImpl
    @PutMapping
    public String updatePersona(@RequestBody Persona persona){
        if(persona.getId_persona() == null || persona.getUsuario() == null || persona.getPassword() == null ||
                persona.getName() == null || persona.getCompany_email() == null ||
                persona.getPersonal_email() == null || persona.getCity() == null ||
                persona.getActive() == null) {
            return new UnprocessableEntityException().getCustomError().toString();
        }
        try{
            return personaServiceImpl.updatePersona(persona).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición delete con un id y llama a la función delete de personaServiceImpl para borrarla
    @DeleteMapping("/{id}")
    public String deletePersona(@PathVariable String id) {
        try{
            personaServiceImpl.deletePersona(id);
            return "Borrado correctamente";
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }
}
