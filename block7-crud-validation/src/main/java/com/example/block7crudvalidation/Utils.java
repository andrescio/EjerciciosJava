package com.example.block7crudvalidation;

import com.example.block7crudvalidation.Persona.Infraestructure.dto.Persona_profesorDTO;
import com.example.block7crudvalidation.Persona.Infraestructure.dto.Persona_studentDTO;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Persona.Infraestructure.dto.PersonaDTO;
import com.example.block7crudvalidation.Profesor.Infraestructure.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Profesor.Infraestructure.dto.ProfesorFullDTO;
import com.example.block7crudvalidation.Student.Infraestructure.Repository.StudentRepository;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student.Infraestructure.dto.StudentFullDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Optional;

// Clase que almacena métodos que se pueden utilizar en diferentes clases y métodos
@Component
public class Utils {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    // Instancia de clase Gson, que permite crear un json a partir de un objeto y darle formato
    Gson gson = new GsonBuilder().setPrettyPrinting()
                                 .excludeFieldsWithoutExposeAnnotation()
                                 .create();

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

    // Método que devuelve un objeto PersonaDTO recibiendo como parámetro una Persona
    public PersonaDTO getPersonaDTO(Persona persona){
        return new PersonaDTO(persona.getId_persona(),
                              persona.getUsuario(),
                              persona.getName(),
                              persona.getSurname(),
                              persona.getCompany_email(),
                              persona.getPersonal_email(),
                              persona.getCity());
    }

    // Método que devuelve un objeto String personaDTOResultado que contiene el DTO que le corresponde a una Persona
    // recibiendo como parámetro la Persona y el outputType
    public String getPersonaDTO(Persona persona, String outputType){
        // Variables para guardar los datos de Student y Profesor, en caso de que la Persona lo sea
        Optional<Student> studentInfo = studentRepository.findByPersona(persona);
        Optional<Profesor> profesorInfo =  profesorRepository.findByPersona(persona);
        // Por defecto asigna al personaDTOResultado un DTO de persona simple
        String personaDTOResultado = gson.toJson(getPersonaDTO(persona));

        // Si se especificase que se quiere el dto completo, modifica el string DTOResultado por el completo del Profesor
        // o Student, según correspondiese
        if(outputType.equals("full")){
            // Si existe studentInfo la persona que se busca es estudiante. Crea su DTO y lo guarda como String en
            // personaDTOResultado
            if(!studentInfo.isEmpty()){
                Persona_studentDTO persona_studentDTO = new Persona_studentDTO(persona.getId_persona(),
                                                                               persona.getUsuario(),
                                                                               persona.getName(),
                                                                               persona.getSurname(),
                                                                               persona.getCompany_email(),
                                                                               persona.getPersonal_email(),
                                                                               persona.getCity(),
                                                                               studentInfo.get());
                personaDTOResultado = gson.toJson(persona_studentDTO);
            }
            // Si existe profesorInfo la persona que se busca es profesor. Crea su DTO y lo guarda como String en
            // personaDTOResultado
            if(!profesorInfo.isEmpty()){
                Persona_profesorDTO persona_profesorDTO = new Persona_profesorDTO(persona.getId_persona(),
                                                                                  persona.getUsuario(),
                                                                                  persona.getName(),
                                                                                  persona.getSurname(),
                                                                                  persona.getCompany_email(),
                                                                                  persona.getPersonal_email(),
                                                                                  persona.getCity(),
                                                                                  profesorInfo.get());
                personaDTOResultado = gson.toJson(persona_profesorDTO);
            }
        }
        return personaDTOResultado;
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

    // Método que devuelve un objeto ProfesorFullDTO recibiendo como parámetro uno Profesor
    public ProfesorFullDTO getProfesorFullDTO(Profesor profesor){
        return new ProfesorFullDTO(profesor.getId_profesor(),
                                   getPersonaDTO(profesor.getPersona()),
                                   profesor.getComments(),
                                   profesor.getBranch());
    }
}
