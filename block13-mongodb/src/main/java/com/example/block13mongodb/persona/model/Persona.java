package com.example.block13mongodb.persona.model;

import com.example.block13mongodb.persona.infraestructure.dto.PersonaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "personas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Persona {
    @Id
    private String id;

    private String usuario;

    private String password;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private Boolean active;

    private Date created_date;

    private String imagen_url;

    private Date termination_date;

    // Method to return this Persona´s DTO
    public PersonaDTO toDTO(){
        return new PersonaDTO(this.id,
                              this.usuario,
                              this.name,
                              this.surname,
                              this.company_email,
                              this.personal_email,
                              this.city);
    }
}
