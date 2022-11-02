package com.example.block7crudvalidation.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    int id_persona;

    String usuario;

    String name;

    String surname;

    String company_email;

    String personal_email;

    String city;
}
