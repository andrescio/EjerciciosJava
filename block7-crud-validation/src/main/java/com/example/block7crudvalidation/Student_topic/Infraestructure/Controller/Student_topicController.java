package com.example.block7crudvalidation.Student_topic.Infraestructure.Controller;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import com.example.block7crudvalidation.Student_topic.Service.Student_topicServiceImpl;
import com.example.block7crudvalidation.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student_topic")
public class Student_topicController {
    @Autowired
    Student_topicServiceImpl student_topicServiceImpl;

    Utils utils = new Utils();

    // Recibe una petición post con los datos de un student_topic y llama a student_topicServiceImpl para que lo añada
    @PostMapping
    public String addStudent_topic(@Valid @RequestBody Student_topic student_topic,
                             BindingResult bindingResult)
    {
        // Si no pasa la validación, llama al método showErrors de la clase Utils y guarda su resultado (los errores)
        // en la variable errors, que se le pasa a la excepción para que los muestre.
        if(bindingResult.hasErrors()){
            String errors = utils.showErrors(bindingResult);
            return new UnprocessableEntityException(errors).getCustomError().toString();
        }
        try{
            return student_topicServiceImpl.addStudent_topic(student_topic).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición GET y devuelve el Student_topic después de pasarle los datos al student_topicServiceImpl
    @GetMapping("/{id}")
    public String getStudent_topicById(@PathVariable int id) {
        try{
            return student_topicServiceImpl.getStudent_topic(id);
        }
        catch (EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición GET a la que le devuelve la lista de todos los student_topic que recibe
    // del método findAllStudent_topic de la clase student_topicServiceImpl
    @GetMapping("/all")
    public String findAllStudent_topic(){
        return student_topicServiceImpl.findAllStudent_topic().toString();
    }

    // Recibe una petición PUT para actualizar los datos de un profesor. Si está correcto, llama a
    // profesorServiceImpl para que lo actualice
    @PutMapping
    public String updateStudent_topic(@Valid @RequestBody Student_topic student_topic,
                                BindingResult bindingResult)
    {
        // Validación de Student_topic
        if(bindingResult.hasErrors()){
            String errors = utils.showErrors(bindingResult);
            return new UnprocessableEntityException(errors).getCustomError().toString();
        }
        try{
            return student_topicServiceImpl.updateStudent_topic(student_topic).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición delete con un id y llama a la función delete de student_topicServiceImpl para borrarlo
    @DeleteMapping("/{id}")
    public String deleteStudent_topic(@PathVariable int id) {
        try{
            student_topicServiceImpl.deleteStudent_topic(id);
            return "Borrado correctamente";
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }

    // Método que recibe una llamada GET para devolver las asignaturas de un estudiante
    @GetMapping("/student/{id}")
    public String getTopicStudent(@PathVariable int id) {
        try{
            return student_topicServiceImpl.getTopicStudent(id).toString();
        }
        catch (EntityNotFoundException e) {
            return e.getCustomError().toString();
        }
    }
}
