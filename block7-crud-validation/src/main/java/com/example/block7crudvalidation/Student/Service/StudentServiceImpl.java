package com.example.block7crudvalidation.Student.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Student.Infraestructure.Repository.StudentRepository;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student.Infraestructure.dto.StudentFullDTO;
import com.example.block7crudvalidation.Student_topic.Infraestructure.Repository.Student_topicRepository;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import com.example.block7crudvalidation.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Clase servicio que realiza las comprobaciones pertinentes y efectúa los métodos de StudentService si procede
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    Student_topicRepository student_topicRepository;

    Utils utils = new Utils();

    // Método que añade a un estudiante
    @Override
    public Student addStudent(Student student) throws EntityNotFoundException {
        Optional<Persona> persona = personaRepository.findById(student.getPersona().getId_persona());
        List<Student_topic> studies = new ArrayList<>();
        if(persona.isEmpty()){
            throw new EntityNotFoundException();
        }
        // Si se indicaron asignaturas, comprueba que existan
        if(student.getStudies() != null){
            for(Student_topic student_topic: student.getStudies()){
                Optional<Student_topic> student_topicActual = student_topicRepository.
                        findById(student_topic.getId_student_topic());
                if(student_topicActual.isEmpty()){
                    throw new EntityNotFoundException("No existe la asignatura asignada");
                }
                studies.add(student_topicActual.get());
            }
        }
        student.setPersona(persona.get());
        student.setStudies(studies);
        student = studentRepository.save(student);
        return student;
    }

    // Método que modifica a un estudiante
    @Override
    public Student updateStudent(Student student) throws EntityNotFoundException {
        Optional<Persona> persona = personaRepository.findById(student.getPersona().getId_persona());
        Optional<Student> searchStudent = studentRepository.findById(student.getId_student());
        if(persona.isEmpty()){
            throw new EntityNotFoundException();
        }
        if(searchStudent.isEmpty()){
            throw new EntityNotFoundException("No existe el estudiante, verifique la ID");
        }
        // Si se indicaron asignaturas, comprueba que existan
        if(student.getStudies() != null){
            for(Student_topic student_topic: student.getStudies()){
                Optional<Student_topic> student_topicActual = student_topicRepository.
                        findById(student_topic.getId_student_topic());
                if(student_topicActual.isEmpty()){
                    throw new EntityNotFoundException("No existe la asignatura asignada");
                }
            }
        }
        return studentRepository.save(student);
    }

    // Método que elimina a un estudiante
    @Override
    public void deleteStudent(int id) throws EntityNotFoundException {
        Optional deletedStudent = studentRepository.findById(id);
        if(deletedStudent.isEmpty()){
            throw new EntityNotFoundException();
        }
        studentRepository.deleteById(id);
    }

    // Método que busca a un estudiante según su ID
    @Override
    public String getStudentById(int id, String outputType) throws EntityNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()){
            throw new EntityNotFoundException();
        }
        if(outputType.equals("full")){
            // Devuelve el DTO completo del Student a través del método getStudentFullDTO de la clase Utils
            StudentFullDTO studentFullDTO = utils.getStudentFullDTO(student.get());
            return studentFullDTO.toString();
        }
        else{
            return student.get().toString();
        }
    }

    // Método que busca a todos los estudiantes y los devuelve como una lista de String, ya que el DTO que
    // contiene dicha lista
    @Override
    public List<String> findAllStudents(String outputType) {
        List<Student> listStudents = Streamable.of(studentRepository.findAll()).toList();
        List<String> listStudentsDTO = new ArrayList<>();
        listStudents.forEach(student -> {
            if(outputType.equals("full")){
                listStudentsDTO.add(utils.getStudentFullDTO(student).toString());
            }
            else{
                listStudentsDTO.add(student.toString());
            }
        });
        return listStudentsDTO;
    }
}
