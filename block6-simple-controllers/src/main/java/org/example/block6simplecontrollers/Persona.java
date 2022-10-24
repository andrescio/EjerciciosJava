package org.example.block6simplecontrollers;

public class Persona {
    private String name;
    private int edad;
    private String ciudad;

    public Persona() {
    }

    public Persona(String name, int edad, String ciudad) {
        this.name = name;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
