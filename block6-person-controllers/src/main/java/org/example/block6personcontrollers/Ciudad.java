package org.example.block6personcontrollers;

import org.springframework.stereotype.Component;

@Component
public class Ciudad {
    private String nombre;
    private int numeroHabitantes;

    public Ciudad(){
    }

    public Ciudad(String nombre, int numeroHabitantes) {
        this.nombre = nombre;
        this.numeroHabitantes = numeroHabitantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroHabitantes() {
        return numeroHabitantes;
    }

    public void setNumeroHabitantes(int numeroHabitantes) {
        this.numeroHabitantes = numeroHabitantes;
    }
}
