package org.example.block6pathvariableheaders;

public class Usuario {

    private final long id;
    private String nombre;

    public Usuario(long id, String content) {
        this.id = id;
        this.nombre = content;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
