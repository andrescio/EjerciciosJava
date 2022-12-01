package com.example.block13mongodb.persona.service;

import com.example.block13mongodb.persona.infraestructure.dto.PersonaDTO;
import com.example.block13mongodb.persona.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    MongoTemplate mongoTemplate;

    // Method that adds a Persona
    @Override
    public Persona addPersona(Persona persona) {
        return mongoTemplate.insert(persona);
    }

    // Method that updates a Persona
    @Override
    public Persona updatePersona(Persona persona) throws Exception {
        Persona existentPersona = mongoTemplate.findById(persona.getId(),Persona.class);
        if(existentPersona == null){
            throw new Exception("Not existent person");
        }
        return mongoTemplate.save(persona,"personas");
    }

    // Method that finds a Person in a mongodb and returns its DTO
    @Override
    public PersonaDTO getPersona(String id) throws Exception {
        Persona findPersona = mongoTemplate.findById(id,Persona.class);
        if(findPersona == null){
            throw new Exception("Not existent person");
        }
        return findPersona.toDTO();
    }

    // Method that deletes a Persona in a mongodb
    @Override
    public String deletePersona(String id){
        Persona findPersona = mongoTemplate.findById(id,Persona.class);
        if(findPersona == null){
            return "Not existent person";
        }
        mongoTemplate.remove(findPersona);
        return "Persona removed correctly";
    }

    // Method that gets all Persona in a mongodb ant returns a paginated list of its dto
    @Override
    public List<PersonaDTO> getAllPersonas(int page, int size) {
        Query query = new Query();
        query.skip((long) page * size);
        query.limit(size);

        List<Persona> personas = mongoTemplate.find(query, Persona.class);
        List<PersonaDTO> personaDTOS = new ArrayList<>();
        for(Persona persona: personas){
            personaDTOS.add(persona.toDTO());
        }

        return personaDTOS;
    }

    // Method that gets all Persona by name in a mongodb ant returns a list of its dto
    @Override
    public List<PersonaDTO> getPersonasByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        List<Persona> personas = mongoTemplate.find(query, Persona.class, "personas");
        List<PersonaDTO> personaDTOS = new ArrayList<>();
        for(Persona persona: personas){
            personaDTOS.add(persona.toDTO());
        }

        return personaDTOS;
    }

    // Method that gets all Persona by usuario in a mongodb ant returns a list of its dto
    @Override
    public List<PersonaDTO> getPersonasByUsuario(String usuario) {
        Query query = new Query();
        query.addCriteria(Criteria.where("usuario").is(usuario));

        List<Persona> personas = mongoTemplate.find(query, Persona.class, "personas");
        List<PersonaDTO> personaDTOS = new ArrayList<>();
        for(Persona persona: personas){
            personaDTOS.add(persona.toDTO());
        }

        return personaDTOS;
    }
}
