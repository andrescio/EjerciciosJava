package com.example.block13mongodb.persona.infraestructure.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonaDTO {
    String id;

    String usuario;

    String name;

    String surname;

    String company_email;

    String personal_email;

    String city;

    public PersonaDTO(String id, String usuario, String name, String surname, String company_email, String personal_email, String city) {
        this.id = id;
        this.usuario = usuario;
        this.name = name;
        this.surname = surname;
        this.company_email = company_email;
        this.personal_email = personal_email;
        this.city = city;
    }


}
