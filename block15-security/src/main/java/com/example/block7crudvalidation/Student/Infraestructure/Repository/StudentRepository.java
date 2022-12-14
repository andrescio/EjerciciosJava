package com.example.block7crudvalidation.Student.Infraestructure.Repository;

import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// Interface con los métodos CRUD por defecto de CrudRepository.
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<Student> findByPersona(Persona persona);
    List<Student> findByProfesor(Profesor profesor);
    List<Student> findByStudies(Student_topic student_topic);
}
