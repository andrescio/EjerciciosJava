package com.example.block7crudvalidation.Persona.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

// Modelo de una persona. Getters y setters generados de forma automática con Lombok
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_persona;

    @Column
    String usuario;

    @Column
    String password;

    @Column
    String name;

    @Column
    String surname;

    @Column
    String company_email;

    @Column
    String personal_email;

    @Column
    String city;

    @Column
    Boolean active;

    @Column
    Date created_date;

    @Column
    String imagen_url;

    @Column
    Date termination_date;

    @Override
    public String toString() {
        return "Persona{" +
                "\n    id_persona: " + id_persona +
                ",\n    usuario: '" + usuario + '\'' +
                ",\n    password: '" + password + '\'' +
                ",\n    name: '" + name + '\'' +
                ",\n    surname: '" + surname + '\'' +
                ",\n    company_email: '" + company_email + '\'' +
                ",\n    personal_email: '" + personal_email + '\'' +
                ",\n    city: '" + city + '\'' +
                ",\n    active: " + active +
                ",\n    created_date: " + created_date +
                ",\n    imagen_url: '" + imagen_url + '\'' +
                ",\n    termination_date: " + termination_date +
                "\n}";
    }
}
