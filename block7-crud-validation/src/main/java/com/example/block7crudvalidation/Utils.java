package com.example.block7crudvalidation;

import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Persona.Infraestructure.dto.PersonaDTO;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Profesor.Infraestructure.dto.ProfesorFullDTO;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student.Infraestructure.dto.StudentFullDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

// Clase que almacena métodos que se pueden utilizar en diferentes clases
public class Utils {

    public Utils() {
    }

    // Método que, recibiendo un bindingResult como parámetro, recorre los errores
    // y los guarda en una variable errors. Devuelve esa variable.
    public String showErrors(BindingResult bindingResult){
        String errors = "";
        for(ObjectError e: bindingResult.getAllErrors()){
            errors +=((FieldError) e).getField() + " " + e.getDefaultMessage()+", ";
        }
        return errors;
    }

    // Método que devuelve un objeto PersonaDTO recibiendo como parámetro uno Persona
    public PersonaDTO getPersonaDTO(Persona persona){
        return new PersonaDTO(persona.getId_persona(),
                              persona.getUsuario(),
                              persona.getName(),
                              persona.getSurname(),
                              persona.getCompany_email(),
                              persona.getPersonal_email(),
                              persona.getCity());
    }

    // Método que devuelve un objeto StudentFullDTO recibiendo como parámetro uno Student
    public StudentFullDTO getStudentFullDTO(Student student){
        return new StudentFullDTO(student.getId_student(),
                                  getPersonaDTO(student.getPersona()),
                                  student.getNum_hours_week(),
                                  student.getComments(),
                                  student.getProfesor(),
                                  student.getBranch(),
                                  student.getStudies());
    }

    // Método que devuelve un objeto ProfesorFullDTO recibiendo como parámetro uno Student
    public ProfesorFullDTO getProfesorFullDTO(Profesor profesor){
        return new ProfesorFullDTO(profesor.getId_profesor(),
                                   getPersonaDTO(profesor.getPersona()),
                                   profesor.getComments(),
                                   profesor.getBranch());
    }
}
