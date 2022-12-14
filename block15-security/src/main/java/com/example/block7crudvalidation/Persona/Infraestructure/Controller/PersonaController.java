package com.example.block7crudvalidation.Persona.Infraestructure.Controller;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Feign;
import com.example.block7crudvalidation.Persona.Infraestructure.dto.PersonaDTO;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Persona.Service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.List;

@RestController
@EnableWebSecurity
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @Autowired
    Feign feign;

    // Método que recibe mediante una petición POST una persona y la añade a la BBDD.
    @CrossOrigin(origins= "https://cdpn.io")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addPersona(@RequestBody Persona persona){
        try{
            return personaServiceImpl.addPersona(persona).toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }

    // Devuelve una persona basándose en su id
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/{id}")
    public String getPersonaById(@PathVariable String id,
                                 @RequestParam(required = false) String outputType)
    {
        try{
            if(outputType == null){
                outputType = "simple";
            }
            return personaServiceImpl.getPersonaById(id,outputType);
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Devuelve personas según su nombre de usuario. Se puede especificar si se desea su información
    // simple o completa (full)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/usuario/{usuario}")
    public String findPersonaByUsuario(@PathVariable String usuario,
                                       @RequestParam(required = false) String outputType){
        return personaServiceImpl.findByUsuario(usuario,outputType).toString();
    }

    // Devuelve la lista de todas las personas
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @CrossOrigin(origins= "https://cdpn.io")
    @GetMapping("/all")
    public String findAllPersonas(@RequestParam(required = false) String outputType){
        if(outputType == null){
            outputType = "simple";
        }
        return personaServiceImpl.findAllPersonas(outputType).toString();
    }

    // Método que recibe los datos de una persona existente y los cambia mediante
    // el método updatePersona de personaServiceImpl
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public String updatePersona(@RequestBody Persona persona){
        if(persona.getId_persona() == null || persona.getUsuario() == null || persona.getPassword() == null ||
                persona.getName() == null || persona.getCompany_email() == null ||
                persona.getPersonal_email() == null || persona.getCity() == null ||
                persona.getActive() == null) {
            return new UnprocessableEntityException().getCustomError().toString();
        }
        try{
            return personaServiceImpl.updatePersona(persona).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición delete con un id y llama a la función delete de personaServiceImpl para borrarla
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public String deletePersona(@PathVariable String id) {
        try{
            personaServiceImpl.deletePersona(id);
            return "Borrado correctamente";
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición get para buscar a un profesor. Lo hace a través de un RestTemplate con una petición
    // GET al controlador de getProfesor de la clase Profesor
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/profesor/{id}")
    public String getProfesor(@PathVariable int id){
        String url = "http://localhost:8081/profesor/" + id;
        return new RestTemplate().getForObject(url, String.class);
    }

    // Recibe una petición GET para buscar a un profesor. Lo busca haciendo uso de Feign
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/profesorFeign/{id}")
    public String getProfesorFeign(@PathVariable int id){
        return feign.getProfesorById(id);
    }

    // Recibe una peticion GET para buscar Personas según determinados criterios. Llama al método getCriteriaResult
    // de personaServiceImpl para conseguir sacarlos
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/criteria")
    public List<PersonaDTO> getCriteriaResult(@RequestParam String clave,
                                              @RequestParam String valor,
                                              @RequestParam(required = false) String ordenar,
                                              @RequestParam int numPagina) throws ParseException {
        if(ordenar == null){
            ordenar = "no";
        }
        return personaServiceImpl.getCriteriaResult(clave,valor,ordenar,numPagina);
    }
}
