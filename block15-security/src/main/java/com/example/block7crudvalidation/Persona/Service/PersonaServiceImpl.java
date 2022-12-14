package com.example.block7crudvalidation.Persona.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Persona.Infraestructure.dto.PersonaDTO;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Profesor.Infraestructure.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student.Infraestructure.Repository.StudentRepository;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    ProfesorRepository profesorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    Utils utils = new Utils();

    @PersistenceContext
    private EntityManager entityManager;

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
        if(persona.getUsuario().length() > 10 || persona.getUsuario().length() < 6)
            throw new UnprocessableEntityException();

        // Si no se le especificó una fecha al crearlo, se le asigna automáticamente la fecha actual
        if(persona.getCreated_date() == null)
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
    public void deletePersona(String id) throws EntityNotFoundException, UnprocessableEntityException {
        Optional<Persona> personaABorrar = personaRepository.findById(id);
        if(personaABorrar.isEmpty()){
            throw new EntityNotFoundException();
        }
        // Comprueba que esa persona no sea Studient ni Profesor a la hora de eliminarla. Si lo es lanza una excepción
        Optional<Profesor> profesorDeleted = profesorRepository.findByPersona(personaABorrar.get());
        Optional<Student> studentDeleted = studentRepository.findByPersona(personaABorrar.get());
        if(!profesorDeleted.isEmpty() || !studentDeleted.isEmpty()){
            throw new UnprocessableEntityException("La persona es o estudiante o profesor, borre ese registro primero");
        }
        personaRepository.deleteById(id);
    }

    // Devuelve un String con el dto de la Persona pasándole como parámetro el id de la persona y
    // un string de si quiere el resultado completo o simple
    @Override
    public String getPersonaById(String id, String outputType) throws EntityNotFoundException {
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

    // Método que busca Personas en la base de datos según determinadas condiciones que se le pasen como parámetro
    @Override
    public List<PersonaDTO> getCriteriaResult(String clave, String valor, String ordenar, int numPagina) throws ParseException {

        // Creación de variables e instancias de las clases necesarias para utilizar criteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);
        Predicate criterioBusqueda = null;

        // Comprueba qué se quiere buscar y guarda esa query en criterioBusqueda
        switch (clave){
            case "usuario":
                criterioBusqueda = cb.like(root.get("usuario"), "%" + valor + "%");
                break;
            case "name":
                criterioBusqueda = cb.like(root.get("name"), "%" + valor + "%");
                break;
            case "surname":
                criterioBusqueda = cb.like(root.get("surname"), "%" + valor + "%");
                break;
            case "created_date":
                criterioBusqueda = cb.lessThan(root.get("created_date"),
                                               new SimpleDateFormat("yyyy/MM/dd").parse(valor));
                break;
        }

        // Comprueba si se requiere ordenar la búsqueda y la ordena
        switch (ordenar){
            case "usuario":
                query.orderBy(cb.asc(root.get("usuario")));
                break;
            case "name":
                query.orderBy(cb.asc(root.get("name")));
                break;
        }

        // Aplica la query y guarda las personas resultantes en una lista de personas
        query.where(criterioBusqueda);
        List<Persona> personas = entityManager.createQuery(query).getResultList();
        List<PersonaDTO> personasDTO = new ArrayList<>();

        // Convierte la lista de Persona a una lista de PersonaDTO
        personas.forEach(persona -> personasDTO.add(utils.getPersonaDTO(persona)));

        // Crea la paginación con la clase PagedListHolder
        PagedListHolder<PersonaDTO> page = new PagedListHolder<PersonaDTO>(personasDTO);
        page.setPageSize(2); // Número de elementos por página
        page.setPage(numPagina); // Número de página a mostrar

        return page.getPageList();  // Devuelve la lista paginada de personas
    }
}
