package com.example.block7crudvalidation.Persona.Infraestructure.dto;

import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona_profesorDTO {

    @Expose
    Integer id_persona;

    @Expose
    String usuario;

    @Expose
    String name;

    @Expose
    String surname;

    @Expose
    String company_email;

    @Expose
    String personal_email;

    @Expose
    String city;

    @Expose
    Profesor profesor;

    @Override
    public String toString() {
        return "Persona_profesorDTO{" +
                "\n   id_persona: " + id_persona +
                ",\n   usuario: '" + usuario + '\'' +
                ",\n   name: '" + name + '\'' +
                ",\n   surname: '" + surname + '\'' +
                ",\n   company_email: '" + company_email + '\'' +
                ",\n   personal_email: '" + personal_email + '\'' +
                ",\n   city: '" + city + '\'' +
                ",\n   profesor: {" +
                "\n      id_profesor: " + profesor.getId_profesor() +
                ",\n      persona: " + profesor.getPersona().getId_persona() +
                ",\n      coments: " + profesor.getComments() +
                ",\n      branch: " + profesor.getBranch() +
                "\n   }" +
                "\n}";
    }
}
