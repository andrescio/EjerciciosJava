package com.example.block7crudvalidation.Persona.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Profesor.Infraestructure.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student.Infraestructure.Repository.StudentRepository;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonaServiceImplTest {

    @Mock
    PersonaRepository personaRepository;

    @Mock
    Utils utils;

    @Mock
    ProfesorRepository profesorRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    Persona persona;

    @InjectMocks
    PersonaServiceImpl personaServiceImpl = new PersonaServiceImpl();

    @BeforeEach
    void beforeEachFunction(){
        persona = new Persona("Persona_1","acm000","contra","Andres","CM",
                "companyEmail","personalEmail","Madrid",false,
                new GregorianCalendar(2058, Calendar.DECEMBER, 12).getTime(),
                null,null);
    }

    @Test
    void addPersona() {
        Mockito.when(personaRepository.save(any(Persona.class))).thenReturn(persona);
        assertNotNull(personaServiceImpl.addPersona(persona));
    }

    @Test
    void updatePersona() throws EntityNotFoundException {
        Mockito.when(personaRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(persona));
        assertNotNull(personaServiceImpl.updatePersona(persona));
    }

    @Test
    void deletePersona() throws EntityNotFoundException {
        Optional<Profesor> profesor = Optional.empty();
        Optional<Student> student = Optional.empty();
        Mockito.when(profesorRepository.findByPersona(any(Persona.class))).thenReturn(profesor);
        Mockito.when(studentRepository.findByPersona(any(Persona.class))).thenReturn(student);
        Mockito.when(personaRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(persona));
        personaServiceImpl.deletePersona(persona.getId_persona());
    }

    @Test
    void getPersonaById() {
        Mockito.when(personaRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(persona));
        Mockito.when(utils.getPersonaDTO(any(Persona.class),any(String.class))).thenReturn(persona.toString());
        assertNotNull(personaServiceImpl.getPersonaById(persona.getId_persona(),"simple"));
    }

    @Test
    void findByUsuario() {
        Mockito.when(personaRepository.findByUsuario(any(String.class))).thenReturn(List.of(persona));
        Mockito.when(utils.getPersonaDTO(any(Persona.class),any(String.class))).thenReturn(persona.toString());
        assertNotNull(personaServiceImpl.findByUsuario(persona.getUsuario(),"simple"));
    }

    @Test
    void findAllPersonas() {
        Mockito.when(personaRepository.findAll()).thenReturn(List.of(persona));
        assertNotNull(personaServiceImpl.findAllPersonas("simple"));
    }

    @Test
    void getCriteriaResult() {

    }
}