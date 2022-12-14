package com.example.block7crudvalidation.Student.Infraestructure.dto;

import com.example.block7crudvalidation.Persona.Infraestructure.dto.PersonaDTO;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFullDTO {

    Integer id_student;

    PersonaDTO persona;

    Integer num_hours_week;

    String comments;

    Profesor profesor;

    String branch;

    List<Student_topic> estudios;

    @Override
    public String toString() {
        return "{" +
                "\n    id_student: " + id_student +
                ",\n    persona: {" +
                "\n        id_persona: " + persona.getId_persona() +
                ",\n        usuario: " + persona.getUsuario() +
                ",\n        name: " + persona.getName() +
                ",\n        surname: " + persona.getSurname() +
                ",\n        company_email: " + persona.getCompany_email() +
                ",\n        personal_email: " + persona.getPersonal_email() +
                ",\n        city: " + persona.getCity() +
                "\n    }" +
                ",\n    num_hours_week: " + num_hours_week +
                ",\n    comments: '" + comments + '\'' +
                ",\n    profesor: {" +
                "\n        id_profesor: " + profesor.getId_profesor() +
                ",\n        persona: " + profesor.getPersona().getId_persona() +
                ",\n        coments: " + profesor.getComments() +
                ",\n        branch: " + profesor.getBranch() +
                "\n    }" +
                ",\n    branch: '" + branch + '\'' +
                ",\n    estudios: " + estudios +
                "\n}";
    }
}
