package org.example.block6simplecontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Clase que recibe personas mediante peticiones post y get y las envía a la clase ServiciosPersonas
// para que las cree.
@RestController
@RequestMapping
public class ControladorPersona {

    @Autowired
    ServiciosPersonas servicioPersona;

    // Hace una petición GET con el nombre de una persona
    @GetMapping(value="/user/{name}")
    public String getEmployeesById(@PathVariable String name) {
        return servicioPersona.buscarPersona(name);
    }

    // Petición POST que añade una persona a la lista de personas
    @PostMapping(value="/useradd")
    public Persona agregarPersonaJSON(@RequestBody Persona persona){
        persona.setEdad(persona.getEdad()+1);
        servicioPersona.agregarPersona(persona);
        return persona;
    }
}
