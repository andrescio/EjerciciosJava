package com.example.block7crudvalidation.Profesor.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Profesor.Infraestructure.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Profesor.Infraestructure.dto.ProfesorFullDTO;
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
public class ProfesorServiceImpl implements ProfesorService{

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    Student_topicRepository student_topicRepository;

    Utils utils = new Utils();

    // Método que añade un profesor a la BBDD
    @Override
    public Profesor addProfesor(Profesor profesor) throws EntityNotFoundException, UnprocessableEntityException {
        Optional<Persona> persona = personaRepository.findById(profesor.getPersona().getId_persona());
        if(persona.isEmpty() == true){
            throw new EntityNotFoundException();
        }
        // Comprueba que la Persona que se intenta asignar no sea ya Profesor o Student
        Optional<Student> personaStudent = studentRepository.findByPersona(persona.get());
        Optional<Profesor> personaProfesor = profesorRepository.findByPersona(persona.get());
        if(!personaProfesor.isEmpty() || !personaStudent.isEmpty()){
            throw new UnprocessableEntityException("Esa Persona ya está asignada");
        }
        profesor.setPersona(persona.get());
        profesor = profesorRepository.save(profesor);
        return profesor;
    }

    // Método que modifica a un Profesor
    @Override
    public Profesor updateProfesor(Profesor profesor) throws EntityNotFoundException {
        Optional<Persona> persona = personaRepository.findById(profesor.getPersona().getId_persona());
        Optional<Profesor> searchProfesor = profesorRepository.findById(profesor.getId_profesor());
        if(persona.isEmpty()){
            throw new EntityNotFoundException();
        }
        if(searchProfesor.isEmpty()){
            throw new EntityNotFoundException("No existe el profesor, verifique la ID");
        }
        // Comprueba que la Persona que se intenta asignar no sea ya Profesor o Student
        // y que, en caso de serlo, sea el propio Profesor que se intenta actualizar
        Optional<Student> personaStudent = studentRepository.findByPersona(persona.get());
        Optional<Profesor> personaProfesor = profesorRepository.findByPersona(persona.get());
        if(!personaProfesor.isEmpty() && personaProfesor.get().getId_profesor() != profesor.getId_profesor() ||
                !personaStudent.isEmpty()){
            throw new UnprocessableEntityException("Esa Persona ya está asignada");
        }
        return profesorRepository.save(profesor);
    }

    // Método que elimina a un Profesor
    @Override
    public void deleteProfesor(int id) throws EntityNotFoundException, UnprocessableEntityException {
        Optional<Profesor> deletedProfesor = profesorRepository.findById(id);
        if(deletedProfesor.isEmpty()){
            throw new EntityNotFoundException();
        }
        // Comprueba que no sea profesor de un alumno o asignatura. En caso de serlo, lanza una excepción
        List<Student> profesorStudent = studentRepository.findByProfesor(deletedProfesor.get());
        List<Student_topic> profesorTopic = student_topicRepository.findByProfesor(deletedProfesor.get());
        if(profesorStudent.size() != 0 || profesorTopic.size() != 0){
            throw new UnprocessableEntityException("El profesor tiene estudiantes o asignaturas asignados, " +
                    "borrelos primero");
        }
        profesorRepository.deleteById(id);
    }

    // Método que busca a un profesor según su ID
    @Override
    public String getProfesorById(int id, String outputType) throws EntityNotFoundException {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        if(profesor.isEmpty()){
            throw new EntityNotFoundException();
        }
        if(outputType.equals("full")){
            // Devuelve el DTO completo del Profesor a través del método getProfesorFullDTO de la clase Utils
            ProfesorFullDTO profesorFullDTO = utils.getProfesorFullDTO(profesor.get());
            return profesorFullDTO.toString();
        }
        else{
            return profesor.get().toString();
        }
    }

    // Método que busca a todos los profesores y los devuelve como una lista de String, ya que el DTO que
    // contiene dicha lista
    @Override
    public List<String> findAllProfesors(String outputType) {
        List<Profesor> listProfesors = Streamable.of(profesorRepository.findAll()).toList();
        List<String> listProfesorsDTO = new ArrayList<>();
        listProfesors.forEach(profesor -> {
            if(outputType.equals("full")){
                listProfesorsDTO.add(utils.getProfesorFullDTO(profesor).toString());
            }
            else{
                listProfesorsDTO.add(profesor.toString());
            }
        });
        return listProfesorsDTO;
    }
}
