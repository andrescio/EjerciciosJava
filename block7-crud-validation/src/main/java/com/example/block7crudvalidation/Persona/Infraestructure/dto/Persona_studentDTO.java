package com.example.block7crudvalidation.Persona.Infraestructure.dto;

import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona_studentDTO {

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
    Student student;

    @Override
    public String toString() {
        String studies = "";
        for(Student_topic topic : student.getStudies()){
            studies += "\n            Student_topic: {";
            studies += "\n                id_student_topic: "+topic.getId_student_topic();
            studies += ",\n                id_profesor: "+topic.getProfesor().getId_profesor();
            studies += ",\n                course: "+topic.getCourse();
            studies += ",\n                comment: "+topic.getComment();
            studies += ",\n                initial_date: "+topic.getInitial_date();
            studies += ",\n                finish_date: "+topic.getFinish_date();
            studies += "\n            }";
        }
        if(!studies.equals("")){
            studies = studies + "\n         ]";
        }

        return "Persona_studentDTO{" +
                "\n    id_persona: " + id_persona +
                ",\n    usuario: '" + usuario + '\'' +
                ",\n    name: '" + name + '\'' +
                ",\n    surname: '" + surname + '\'' +
                ",\n    company_email: '" + company_email + '\'' +
                ",\n    personal_email: '" + personal_email + '\'' +
                ",\n    city: '" + city + '\'' +
                "\n    student: {" +
                "\n        id_student: " + student.getId_student() +
                ",\n        id_persona: " + student.getPersona().getId_persona() +
                ",\n        num_hours_week: " + student.getNum_hours_week() +
                ",\n        comments: '" + student.getComments() + '\'' +
                ",\n        profesor: {" +
                "\n            id_profesor: " + student.getProfesor().getId_profesor() +
                ",\n            persona: " + student.getProfesor().getPersona().getId_persona() +
                ",\n            coments: " + student.getProfesor().getComments() +
                ",\n            branch: " + student.getProfesor().getBranch() +
                "\n         }" +
                ",\n        branch: '" + student.getBranch() + '\'' +
                ",\n        studies: [" + studies +
                "\n    }" +
                "\n}";
    }
}
