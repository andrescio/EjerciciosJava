package com.example.block7crudvalidation.Student_topic.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Profesor.Infraestructure.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student.Infraestructure.Repository.StudentRepository;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student_topic.Infraestructure.Repository.Student_topicRepository;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import com.example.block7crudvalidation.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Student_topicServiceImpl implements Student_topicService{

    @Autowired
    Student_topicRepository student_topicRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    Utils utils = new Utils();

    // Método que añade un student_topic a la BBDD. Lanza excepción si no se encuentra la persona o estudiante
    // del student_topic
    @Override
    public Student_topic addStudent_topic(Student_topic student_topic) throws EntityNotFoundException {
        //Optional<Student> student = studentRepository.findById(student_topic.getStudent().getId_student());
        Optional<Profesor> profesor = profesorRepository.findById(student_topic.getProfesor().getId_profesor());
        if(profesor.isEmpty()){
            throw new EntityNotFoundException();
        }
        //student_topic.setStudent(student.get());
        student_topic.setProfesor(profesor.get());
        student_topic = student_topicRepository.save(student_topic);
        return student_topic;
    }

    @Override
    public Student_topic updateStudent_topic(Student_topic student_topic) throws Exception {
        return null;
    }

    @Override
    public void deleteStudent_topic(int id) throws Exception {

    }

    @Override
    public String getStudent_topic(int id, String outputType) throws Exception {
        return null;
    }

    @Override
    public List<String> findAllStudent_topic(String outputType) {
        return null;
    }
}
