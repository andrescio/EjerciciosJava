package com.example.block7crudvalidation.Profesor.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Profesor.Infraestructure.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Profesor.Infraestructure.dto.ProfesorFullDTO;
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

    Utils utils = new Utils();

    // Método que añade un profesor a la BBDD
    @Override
    public Profesor addProfesor(Profesor profesor) throws EntityNotFoundException {
        Optional<Persona> persona = personaRepository.findById(profesor.getPersona().getId_persona());
        if(persona.isEmpty() == true){
            throw new EntityNotFoundException();
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
        return profesorRepository.save(profesor);
    }

    // Método que elimina a un Profesor
    @Override
    public void deleteProfesor(int id) throws EntityNotFoundException {
        Optional deletedProfesor = profesorRepository.findById(id);
        if(deletedProfesor.isEmpty()){
            throw new EntityNotFoundException();
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
