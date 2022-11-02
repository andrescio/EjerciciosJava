package com.example.block7crudvalidation.Services;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Interfaces.PersonaRepository;
import com.example.block7crudvalidation.Interfaces.PersonaService;
import com.example.block7crudvalidation.Models.Persona;
import com.example.block7crudvalidation.Models.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// Clase servicio que realiza las comprobaciones pertinentes y efectúa los métodos de PersonaService si procede
@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    // Método que recibe una persona a través del controlador PersonaController, hace las validaciones y la añade si
    // está correcto. En caso contrario lanza una excepción.
    @Override
    public Persona addPersona(Persona persona) throws UnprocessableEntityException {
        Date date = new Date();

        // Comprueba que los campos obligatorios no sean nulos
        if(persona.getUsuario() == null || persona.getPassword() == null || persona.getName() == null ||
                persona.getCompany_email() == null || persona.getPersonal_email() == null || persona.getCity() == null ||
                persona.getActive() == null){
            throw new UnprocessableEntityException();
        }

        //Comprueba la longitud del nombre de usuario
        if(persona.getUsuario().length() > 10 || persona.getUsuario().length() < 6){
            throw new UnprocessableEntityException();
        }

        persona.setCreated_date(date);
        personaRepository.save(persona);
        return persona;
    }

    // Método que actualiza los datos de una persona
    @Override
    public Persona updatePersona(Persona persona) throws EntityNotFoundException {
        Optional<Persona> personaAModificar = personaRepository.findById(persona.getId_persona());
        if(personaAModificar.isEmpty() == true){
            throw new EntityNotFoundException();
        }
        personaRepository.save(persona);
        return persona;
    }

    // Método que borra una persona según su ID
    @Override
    public void deletePersona(int id) throws EntityNotFoundException {
        Optional personaABorrar = personaRepository.findById(id);
        if(personaABorrar.isEmpty() == true){
            throw new EntityNotFoundException();
        }
        personaRepository.deleteById(id);
    }

    // Devuelve un objeto PersonaDTO con los valores de la Persona con el id que se pase por parámetro
    @Override
    public PersonaDTO getPersonaById(int id) throws EntityNotFoundException {
        Optional<Persona> persona = personaRepository.findById(id);
        if(persona.isEmpty() == true){
            throw new EntityNotFoundException();
        }
        PersonaDTO personaDTO = new PersonaDTO(persona.get().getId_persona(),
                                                persona.get().getUsuario(),
                                                persona.get().getName(),
                                                persona.get().getSurname(),
                                                persona.get().getCompany_email(),
                                                persona.get().getPersonal_email(),
                                                persona.get().getCity());
        return personaDTO;
    }

    // Devuelve una lista de PersonaDTO según el usuario que se pase por parámetro
    @Override
    public List<PersonaDTO> findByUsuario(String usuario) {
        List<Persona> listaPersonas = personaRepository.findByUsuario(usuario);
        List<PersonaDTO> listaPersonasDTO = new ArrayList<>();
        listaPersonas.forEach(persona -> {
            PersonaDTO personaDTO = new PersonaDTO(persona.getId_persona(),
                                                    persona.getUsuario(),
                                                    persona.getName(),
                                                    persona.getSurname(),
                                                    persona.getCompany_email(),
                                                    persona.getPersonal_email(),
                                                    persona.getCity());
            listaPersonasDTO.add(personaDTO);
        });
        return listaPersonasDTO;
    }

    // Devuelve todas las personas que haya como una lista de PersonaDTO
    @Override
    public List<PersonaDTO> findAllPersonas() {
        List<Persona> listaPersonas = Streamable.of(personaRepository.findAll()).toList();
        List<PersonaDTO> listaPersonasDTO = new ArrayList<>();
        listaPersonas.forEach(persona -> {
            PersonaDTO personaDTO = new PersonaDTO(persona.getId_persona(),
                                                    persona.getUsuario(),
                                                    persona.getName(),
                                                    persona.getSurname(),
                                                    persona.getCompany_email(),
                                                    persona.getPersonal_email(),
                                                    persona.getCity());
            listaPersonasDTO.add(personaDTO);
        });
        return listaPersonasDTO;
    }
}
