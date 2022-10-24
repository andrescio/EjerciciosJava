package org.example.block6simplecontrollers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Clase que crea las personas recibidas por la clase ControladorPersona
@Service
public class ServiciosPersonas {
    private List<Persona> personas = new ArrayList<>();

    // Agrega una persona
    public void agregarPersona(Persona persona){
        personas.add(persona);
    }

    // Devuelve una persona en específico basándose en el nombre
    public String buscarPersona(String nombre){
        for(Persona p: personas){
            if(p.getName().equals(nombre)){
                return "Hola "+nombre;
            }
        }
        return "";
    }
}