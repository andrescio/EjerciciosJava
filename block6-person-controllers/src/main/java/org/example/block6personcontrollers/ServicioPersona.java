package org.example.block6personcontrollers;

import org.springframework.stereotype.Service;

@Service
public class ServicioPersona {
    private Persona persona;

    // Guarda los datos recibidos por el controlador en la persona creada.
    public Persona crearPersona(String name, int edad, String ciudad){
        persona = new Persona(name,edad,ciudad);
        return persona;
    }

    // Muestra la persona guardada
    public Persona getPersona(){
        return persona;
    }
}
