package org.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/controlador2")
public class Controlador2 {
    @Autowired
    private ServicioPersona servicioPersona;

    @Autowired
    private ServicioCiudad servicioCiudad;

    // Muestra la persona creada por el controlador 1 pero multiplicando su edad por 2.
    // Recibe una llamada GET y llama al método getPersona de la clase ServicioPersona para que muestre la persona.
    @GetMapping(value="/getPersona")
    public Persona getPersona(){
        int edad = servicioPersona.getPersona().getEdad();
        servicioPersona.getPersona().setEdad(edad * 2);
        return servicioPersona.getPersona();
    }

    // Recibe una llamada GET para mostrar la lista de ciudades. Envía esa petición se ServicioCiudad, que
    // es el método que se encarga de mostrarlas.
    @GetMapping(value="/getCiudad")
    public List<Ciudad> getCiudades(){
        return servicioCiudad.getCiudades();
    }
}
