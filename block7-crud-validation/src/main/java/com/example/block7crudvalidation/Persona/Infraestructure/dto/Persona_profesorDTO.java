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
    String id_persona;

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
}
