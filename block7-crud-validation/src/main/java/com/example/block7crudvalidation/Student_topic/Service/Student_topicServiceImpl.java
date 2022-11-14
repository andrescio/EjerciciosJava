package com.example.block7crudvalidation.Student_topic.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Persona.Infraestructure.dto.PersonaDTO;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Profesor.Infraestructure.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Profesor.Infraestructure.dto.ProfesorFullDTO;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student.Infraestructure.Repository.StudentRepository;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student_topic.Infraestructure.Repository.Student_topicRepository;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import com.example.block7crudvalidation.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Optional<Profesor> profesor = profesorRepository.findById(student_topic.getProfesor().getId_profesor());
        if(profesor.isEmpty()){
            throw new EntityNotFoundException();
        }
        student_topic.setProfesor(profesor.get());
        student_topic = student_topicRepository.save(student_topic);
        return student_topic;
    }

    // Método que actualiza un student_topic
    @Override
    public Student_topic updateStudent_topic(Student_topic student_topic) throws EntityNotFoundException {
        // Comprueba que existen el profesor que recibe por la petición PUT como el propio student_topic
        Optional<Student_topic> searchStudent_topic = student_topicRepository.findById(student_topic.getId_student_topic());
        Optional<Profesor> profesor = profesorRepository.findById(student_topic.getProfesor().getId_profesor());
        if(searchStudent_topic.isEmpty()){
            throw new EntityNotFoundException();
        }
        if(profesor.isEmpty()){
            throw new EntityNotFoundException("No existe el profesor, verifique la ID");
        }
        return student_topicRepository.save(student_topic);
    }

    // Método que elimina a un Student_topic
    @Override
    public void deleteStudent_topic(int id) throws EntityNotFoundException, UnprocessableEntityException {
        Optional<Student_topic> deletedStudent_topic = student_topicRepository.findById(id);
        if(deletedStudent_topic.isEmpty()){
            throw new EntityNotFoundException();
        }
        // Comprueba que no tenga estudiantes con su asignatura a la hora de borrarse
        List<Student> students = studentRepository.findByStudies(deletedStudent_topic.get());
        if(students.size() != 0){
            throw new UnprocessableEntityException("Tiene students a cargo, elimínelos antes");
        }
        student_topicRepository.deleteById(id);
    }

    // Método que busca un Student_topic por ID
    @Override
    public String getStudent_topic(int id) throws EntityNotFoundException {
        Optional<Student_topic> student_topic = student_topicRepository.findById(id);
        if(student_topic.isEmpty()){
            throw new EntityNotFoundException();
        }
        return student_topic.get().toString();
    }

    // Método que busca todos los student_topic
    @Override
    public List<Student_topic> findAllStudent_topic() {
        List<Student_topic> listStudent_topic = Streamable.of(student_topicRepository.findAll()).toList();
        return listStudent_topic;
    }

    // Método que devuelve las asignaturas de un estudiante, recibiendo su id como parámetro
    @Override
    public List<Student_topic> getTopicStudent(int id) throws EntityNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()){
            throw new EntityNotFoundException();
        }
        return student.get().getStudies();
    }

}
