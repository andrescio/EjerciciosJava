package org.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Clase que recibe datos a través de peticiones REST
@RestController
@RequestMapping(value="/controlador1")
public class Controlador1 {

    @Autowired
    private ServicioPersona servicioPersona;

    @Autowired
    private ServicioCiudad servicioCiudad;

    // Recibe una persona a través de una petición GET y se la pasa a la clase
    // ServicioPersona para que la guarde.
    @GetMapping(value="/addPersona")
    public Persona addPersona(String name, int edad, String ciudad){
        return servicioPersona.crearPersona(name,edad,ciudad);
    }

    // Recibes los datos de una ciudad a través de una petición POST y la pasa
    // a la clase ServicioCiudad, que la añade a la lista de ciudades.
    @PostMapping(value="/addCiudad")
    public void addCiudad(@RequestBody Ciudad ciudad){
        servicioCiudad.addCiudad(ciudad);
    }

}
