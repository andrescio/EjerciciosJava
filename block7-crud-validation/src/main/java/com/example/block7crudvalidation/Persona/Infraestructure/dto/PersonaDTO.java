package com.example.block7crudvalidation.Persona.Infraestructure.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
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
