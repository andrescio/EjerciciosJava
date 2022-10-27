package com.example.block7crud.Controller;

import com.example.block7crud.Services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/persona")
@ResponseBody
public class DeleteController {

    @Autowired
    private PersonaServiceImpl personaServiceImpl;

    @DeleteMapping("/{id}")
    public String deletePersona(@PathVariable int id){
        try{
            personaServiceImpl.deletePersona(id);
            return "Borrado correctamente";
        }
        catch(Exception e){
            return "No existe el id de la persona que intenta eliminar";
        }
    }
}
