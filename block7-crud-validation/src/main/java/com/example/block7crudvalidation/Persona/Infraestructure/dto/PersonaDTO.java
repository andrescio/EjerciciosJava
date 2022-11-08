package com.example.block7crudvalidation.Persona.Infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    Integer id_persona;

    String usuario;

    String name;

    String surname;

    String company_email;

    String personal_email;

    String city;

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "\n   id_persona: " + id_persona +
                ",\n   usuario: '" + usuario + '\'' +
                ",\n   name: '" + name + '\'' +
                ",\n   surname: '" + surname + '\'' +
                ",\n   company_email: '" + company_email + '\'' +
                ",\n   personal_email: '" + personal_email + '\'' +
                ",\n   city: '" + city + '\'' +
                "\n}";
    }
}
