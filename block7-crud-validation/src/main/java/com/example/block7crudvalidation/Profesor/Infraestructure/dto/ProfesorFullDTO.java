package com.example.block7crudvalidation.Profesor.Infraestructure.dto;

import com.example.block7crudvalidation.Persona.Infraestructure.dto.PersonaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorFullDTO {

    Integer id_profesor;

    PersonaDTO persona;

    String comments;

    String branch;

    @Override
    public String toString() {
        return "Profesor{" +
                "\n    id_profesor: " + id_profesor +
                ",\n    persona: {" +
                "\n        id_persona: " + persona.getId_persona() +
                ",\n        usuario: " + persona.getUsuario() +
                ",\n        name: " + persona.getName() +
                ",\n        surname: " + persona.getSurname() +
                ",\n        company_email: " + persona.getCompany_email() +
                ",\n        personal_email: " + persona.getPersonal_email() +
                ",\n        city: " + persona.getCity() +
                "\n    }" +
                ",\n    comments: '" + comments + '\'' +
                ",\n    branch: '" + branch + '\'' +
                "\n}";
    }
}
