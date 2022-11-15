package com.example.block7crudvalidation.Student.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Profesor.Infraestructure.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
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
    ProfesorRepository profesorRepository;

    @Autowired
    Student_topicRepository student_topicRepository;

    Utils utils = new Utils();

    // Método que añade a un estudiante
    @Override
    public Student addStudent(Student student) throws EntityNotFoundException, UnprocessableEntityException {
        //Comprueba que existen tanto la Persona como el Profesor
        Optional<Persona> persona = personaRepository.findById(student.getPersona().getId_persona());
        Optional<Profesor> profesor = profesorRepository.findById(student.getProfesor().getId_profesor());
        List<Student_topic> studies = new ArrayList<>();
        if(persona.isEmpty() || profesor.isEmpty()){
            throw new EntityNotFoundException();
        }
        // Comprueba que la Persona que se intenta asignar no sea ya Profesor o Student
        Optional<Student> personaStudent = studentRepository.findByPersona(persona.get());
        Optional<Profesor> personaProfesor = profesorRepository.findByPersona(persona.get());
        if(!personaStudent.isEmpty() || !personaProfesor.isEmpty()){
            throw new UnprocessableEntityException("Esa Persona ya está asignada");
        }
        // Si se indicaron asignaturas, comprueba que existen
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
        student.setProfesor(profesor.get());
        student.setStudies(studies);
        return studentRepository.save(student);
    }

    // Método que modifica a un estudiante
    @Override
    public Student updateStudent(Student student) throws EntityNotFoundException, UnprocessableEntityException {
        //Comprueba que existen tanto la Persona como el Profesor o el propio Student
        Optional<Persona> persona = personaRepository.findById(student.getPersona().getId_persona());
        Optional<Profesor> profesor = profesorRepository.findById(student.getProfesor().getId_profesor());
        Optional<Student> searchStudent = studentRepository.findById(student.getId_student());
        if(persona.isEmpty() || profesor.isEmpty()){
            throw new EntityNotFoundException();
        }
        if(searchStudent.isEmpty()){
            throw new EntityNotFoundException("No existe el estudiante, verifique la ID");
        }
        // Comprueba que la Persona que se intenta asignar no sea ya Profesor o Student
        // y que, en caso de serlo, sea el propio Student que se intenta actualizar
        Optional<Student> personaStudent = studentRepository.findByPersona(persona.get());
        Optional<Profesor> personaProfesor = profesorRepository.findByPersona(persona.get());
        if(!personaStudent.isEmpty() && personaStudent.get().getId_student() != student.getId_student() ||
                !personaProfesor.isEmpty()){
            throw new UnprocessableEntityException("Esa Persona ya está asignada");
        }
        // Si se indicaron asignaturas, comprueba que existen
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
        Optional<Student> deletedStudent = studentRepository.findById(id);
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

    // Método que busca a todos los estudiantes y los devuelve como una lista de String.
    @Override
    public List<String> findAllStudents(String outputType) {
        List<Student> listStudents = Streamable.of(studentRepository.findAll()).toList();
        List<String> listStudentsDTO = new ArrayList<>();
        // Añade a listStudentsDTO el estudiante como un String, y si se especifica que quiere todos
        // sus datos, invoca a utils para que los saque.
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

    // Método que asigna una lista de Student_topic a un Student
    @Override
    public Student assignStudent_topic(List<Student_topic> student_topics, int idStudent) throws EntityNotFoundException{
        // Verifica que existen las asignaturas y el estudiante
        Optional<Student> studentExistent = studentRepository.findById(idStudent);
        if(studentExistent.isEmpty()){
            throw new EntityNotFoundException("No existe el Student");
        }
        // Lista de las Student_topic que tiene actualmente el Student
        List<Student_topic> actualStudent_topic = studentExistent.get().getStudies();
        Optional<Student_topic> student_topicExistent;
        // Comprueba que existen los Student_topic que se le intentan asignar.
        for(Student_topic student_topic: student_topics){
            student_topicExistent = student_topicRepository.findById(student_topic.getId_student_topic());
            if(student_topicExistent.isEmpty()){
                throw new EntityNotFoundException("No existe alguno de los Student_topic");
            }
            // Por cada iteración del bucle añade el Student_topic a la lista de actualStudent_topic, excepto si
            // ya está en ella.
            if(!actualStudent_topic.contains(student_topicExistent.get())){
                actualStudent_topic.add(student_topicExistent.get());
            }
        }
        // Modifica al Student añadiendo la lista de sus antiguos Student_topic junto a los nuevos
        studentExistent.get().setStudies(actualStudent_topic);
        return studentRepository.save(studentExistent.get());
    }

    // Método que desasigna una lista de Student_topic a un Student

    public Student deallocateStudent_topic(List<Student_topic> student_topics, int idStudent){
        // Verifica que existen las asignaturas y el estudiante
        Optional<Student> studentExistent = studentRepository.findById(idStudent);
        if(studentExistent.isEmpty()){
            throw new EntityNotFoundException("No existe el Student");
        }
        // Lista de las Student_topic que tiene actualmente el Student
        List<Student_topic> actualStudent_topic = studentExistent.get().getStudies();
        Optional<Student_topic> student_topicExistent;
        // Comprueba que existen los Student_topic que se le intentan desasignar.
        for(Student_topic student_topic: student_topics){
            student_topicExistent = student_topicRepository.findById(student_topic.getId_student_topic());
            if(student_topicExistent.isEmpty()){
                throw new EntityNotFoundException("No existe alguno de los Student_topic");
            }
            // Por cada iteración del bucle elimina el Student_topic a la lista de actualStudent_topic, excepto si
            // no está en ella.
            if(actualStudent_topic.contains(student_topicExistent.get())){
                actualStudent_topic.remove(student_topicExistent.get());
            }
        }
        // Modifica al Student eliminando de su lista los Student_topic que se hayan solicitado
        studentExistent.get().setStudies(actualStudent_topic);
        return studentRepository.save(studentExistent.get());
    }
}
