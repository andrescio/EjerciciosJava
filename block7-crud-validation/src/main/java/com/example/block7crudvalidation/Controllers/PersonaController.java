package com.example.block7crudvalidation.Controllers;

import com.example.block7crudvalidation.Models.Persona;
import com.example.block7crudvalidation.Models.PersonaDTO;
import com.example.block7crudvalidation.Services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        catch(Exception e){
            return e.toString();
        }
    }

    // Devuelve una persona basándose en su id
    @GetMapping("/{id}")
    public String getPersonaById(@PathVariable int id){
        try{
            return personaServiceImpl.getPersonaById(id).toString();
        }
        catch(Exception e){
            return e.toString();
        }
    }

    // Devuelve personas según su nombre de usuario
    @GetMapping("/usuario/{usuario}")
    public String findPersonaByUsuario(@PathVariable String usuario){
        return personaServiceImpl.findByUsuario(usuario).toString();
    }

    // Devuelve la lista de todas las personas
    @GetMapping("/all")
    public List<PersonaDTO> findAllPersonas(){
        return personaServiceImpl.findAllPersonas();
    }

    // Método que recibe los datos de una persona existente y los cambia mediante
    // el método updatePersona de personaServiceImpl
    @PutMapping
    public String updatePersona(@RequestBody Persona persona){
        if(persona.getId_persona() == 0 || persona.getUsuario() == null || persona.getPassword() == null ||
                persona.getName() == null || persona.getCompany_email() == null ||
                persona.getPersonal_email() == null || persona.getCity() == null ||
                persona.getActive() == null) {
            return "Los campos 'id_persona', 'usuario', 'password', 'name', 'company_email'," +
                    "'personal_email', 'city' y 'active' son obligatorios";
        }
        try{
            return personaServiceImpl.updatePersona(persona).toString();
        }
        catch(Exception e){
            return e.toString();
        }
    }

    // Recibe una petición delete con un id y llama a la función delete de personaServiceImpl para borrarla
    @DeleteMapping("/{id}")
    public String deletePersona(@PathVariable int id) {
        try{
            personaServiceImpl.deletePersona(id);
            return "Borrado correctamente";
        }
        catch(Exception e){
            return "No existe el id de la persona que intenta eliminar";
        }
    }
}
