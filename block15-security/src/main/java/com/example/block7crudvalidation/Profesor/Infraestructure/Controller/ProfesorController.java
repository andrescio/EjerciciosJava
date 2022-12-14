package com.example.block7crudvalidation.Profesor.Infraestructure.Controller;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Profesor.Service.ProfesorServiceImpl;
import com.example.block7crudvalidation.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableWebSecurity
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    ProfesorServiceImpl profesorServiceImpl;

    Utils utils = new Utils();

    // Recibe una petición post con los datos de un profesor y llama a profesorServiceImpl para que lo añada
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String addStudent(@Valid @RequestBody Profesor profesor,
                             BindingResult bindingResult)
    {
        // Si no pasa la validación, llama al método showErrors de la clase Utils y guarda su resultado (los errores)
        // en la variable errors, que se le pasa a la excepción para que los muestre.
        if(bindingResult.hasErrors()){
            String errors = utils.showErrors(bindingResult);
            return new UnprocessableEntityException(errors).getCustomError().toString();
        }
        try{
            return profesorServiceImpl.addProfesor(profesor).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición PUT para actualizar los datos de un profesor. Si está correcto, llama a
    // profesorServiceImpl para que lo actualice
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public String updateProfesor(@Valid @RequestBody Profesor profesor,
                                BindingResult bindingResult)
    {
        // Validación de Profesor
        if(bindingResult.hasErrors()){
            String errors = utils.showErrors(bindingResult);
            return new UnprocessableEntityException(errors).getCustomError().toString();
        }
        try{
            return profesorServiceImpl.updateProfesor(profesor).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición GET y devuelve el Profesor después de pasarle los datos al sprofesorServiceImpl
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/{id}")
    public String getProfesorById(@PathVariable int id,
                                 @RequestParam(required = false) String outputType)
    {
        try{
            if(outputType == null){
                outputType = "simple";
            }
            return profesorServiceImpl.getProfesorById(id,outputType);
        }
        catch (EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición GET a la que le devuelve la lista de todos los profesores que recibe
    // del método findAllProfesors de la clase profesorServiceImpl
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/all")
    public String findAllProfesors(@RequestParam(required = false) String outputType){
        if(outputType == null){
            outputType = "simple";
        }
        return profesorServiceImpl.findAllProfesors(outputType).toString();
    }

    // Recibe una petición delete con un id y llama a la función delete de profesorServiceImpl para borrarlo
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteProfesor(@PathVariable int id) {
        try{
            profesorServiceImpl.deleteProfesor(id);
            return "Borrado correctamente";
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }
}
