package com.example.block7crudvalidation.Persona.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Persona.Infraestructure.dto.PersonaDTO;
import com.example.block7crudvalidation.Utils;
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

    @Autowired
    Utils utils = new Utils();

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
        if(personaAModificar.isEmpty()){
            throw new EntityNotFoundException();
        }
        personaRepository.save(persona);
        return persona;
    }

    // Método que borra una persona según su ID
    @Override
    public void deletePersona(int id) throws EntityNotFoundException {
        Optional<Persona> personaABorrar = personaRepository.findById(id);
        if(personaABorrar.isEmpty()){
            throw new EntityNotFoundException();
        }
        personaRepository.deleteById(id);
    }

    // Devuelve un String con el dto de la Persona pasándole como parámetro el id de la persona y
    // un string de si quiere el resultado completo o simple
    @Override
    public String getPersonaById(int id, String outputType) throws EntityNotFoundException {
        // Comprueba que existe la Persona con ese id
        Optional<Persona> persona = personaRepository.findById(id);
        if(persona.isEmpty()){
            throw new EntityNotFoundException();
        }
        return utils.getPersonaDTO(persona.get(), outputType);
    }

    // Devuelve una lista de PersonaDTO según el usuario que se pase por parámetro
    @Override
    public List<String> findByUsuario(String usuario, String outputType) {
        // Guarda en listaPersonas las personas que tengan como usuario el que se especifique en la petición
        List<Persona> listaPersonas = personaRepository.findByUsuario(usuario);
        List<String> listaPersonasDTO = new ArrayList<>();
        // Por cada persona encontrada, saca su DTO a través del método utils.getPersonaDTO
        // y lo guarda en listaPersonasDTO
        listaPersonas.forEach(persona -> listaPersonasDTO.add(utils.getPersonaDTO(persona, outputType)));
        return listaPersonasDTO;
    }

    // Devuelve todas las personas que haya como una lista de String. Si se especifica que quiere sus
    // valores completos con outputType, los saca a través del método utils.getPersonaDTO
    @Override
    public List<String> findAllPersonas(String outputType) {
        // Guarda en listaPersonas las personas existentes
        List<Persona> listaPersonas = Streamable.of(personaRepository.findAll()).toList();
        List<String> listaPersonasDTO = new ArrayList<>();
        // Por cada persona encontrada, saca su DTO a través del método utils.getPersonaDTO
        // y lo guarda en listaPersonasDTO
        listaPersonas.forEach(persona -> listaPersonasDTO.add(utils.getPersonaDTO(persona, outputType)));
        return listaPersonasDTO;
    }
}
