package com.example.block7crudvalidation.Persona.Infraestructure.Controller;

import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Persona.Service.PersonaServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonaControllerTest {

    @Mock
    PersonaServiceImpl personaServiceImpl;

    @Mock
    PersonaRepository personaRepository;

    @InjectMocks
    PersonaController personaController;

    @Mock
    Persona persona;

    @BeforeEach
    void beforeEachFunction(){
        persona = new Persona("Persona_1","acm000","contra","Andres","CM",
                "companyEmail","personalEmail","Madrid",false,
                new GregorianCalendar(2058, Calendar.DECEMBER, 12).getTime(),
                null,null);
    }

    @Test
    void addPersona() {
        Mockito.when(personaServiceImpl.addPersona(any(Persona.class))).thenReturn(persona);
        assertNotNull(personaController.addPersona(persona));
    }

    @Test
    void getPersonaById() {
        Mockito.when(personaServiceImpl.getPersonaById(any(String.class),any(String.class))).thenReturn(persona.toString());
        assertNotNull(personaController.getPersonaById(persona.getId_persona(),""));
    }

    @Test
    void findPersonaByUsuario() {
        Mockito.when(personaServiceImpl.findByUsuario(any(String.class),any(String.class))).thenReturn(List.of(persona.toString()));
        assertNotNull(personaController.findPersonaByUsuario(persona.getUsuario(),""));
    }

    @Test
    void findAllPersonas() {
        Mockito.when(personaServiceImpl.findAllPersonas("")).thenReturn(List.of(persona.toString()));
        assertNotNull(personaController.findAllPersonas(""));
    }

    @Test
    void updatePersona() {
        Mockito.when(personaRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(persona));
        Mockito.when(personaServiceImpl.updatePersona(any(Persona.class))).thenReturn(persona);
        persona.setName("kktua");
        personaController.updatePersona(persona);
    }

    @Test
    void deletePersona() {
        Mockito.when(personaRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(persona));
        personaController.deletePersona(persona.getId_persona());
    }

    @Test
    void getProfesor() {
    }

    @Test
    void getProfesorFeign() {
    }

    @Test
    void getCriteriaResult() {
    }
}