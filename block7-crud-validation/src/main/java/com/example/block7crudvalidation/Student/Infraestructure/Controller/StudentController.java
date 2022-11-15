package com.example.block7crudvalidation.Student.Infraestructure.Controller;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student.Service.StudentServiceImpl;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import com.example.block7crudvalidation.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    Utils utils = new Utils();

    // Recibe una petición post con los datos de un estudiante y llama a studentServiceImpl para que lo añada
    @PostMapping
    public String addStudent(@Valid @RequestBody Student student,
                             BindingResult bindingResult)
    {
        // Si no pasa la validación, llama al método showErrors de la clase Utils y guarda su resultado (los errores)
        // en la variable errors, que se le pasa a la excepción para que los muestre.
        if(bindingResult.hasErrors()){
            String errors = utils.showErrors(bindingResult);
            return new UnprocessableEntityException(errors).getCustomError().toString();
        }
        try{
            return studentServiceImpl.addStudent(student).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición GET y devuelve el Student después de pasarle los datos al studentServiceImpl
    @GetMapping("/{id}")
    public String getStudentById(@PathVariable int id,
                                 @RequestParam(required = false) String outputType)
    {
        try{
            if(outputType == null){
                outputType = "simple";
            }
            return studentServiceImpl.getStudentById(id,outputType);
        }
        catch (EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición GET a la que le devuelve la lista de todos los estudiantes que recibe
    // del método findAllStudents de la clase studentServiceImpl
    @GetMapping("/all")
    public String findAllStudents(@RequestParam(required = false) String outputType){
        if(outputType == null){
            outputType = "simple";
        }
        return studentServiceImpl.findAllStudents(outputType).toString();
    }

    // Recibe una petición PUT para actualizar los datos de un estudiante. Si está correcto, llama a
    // studentServiceImpl para que lo actualice
    @PutMapping
    public String updateStudent(@Valid @RequestBody Student student,
                                BindingResult bindingResult)
    {
        // Validación de Student
        if(bindingResult.hasErrors()){
            String errors = utils.showErrors(bindingResult);
            return new UnprocessableEntityException(errors).getCustomError().toString();
        }
        try{
            return studentServiceImpl.updateStudent(student).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
        catch(UnprocessableEntityException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición delete con un id y llama a la función delete de studentServiceImpl para borrarla
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        try{
            studentServiceImpl.deleteStudent(id);
            return "Borrado correctamente";
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición PUT para asignar una o más asignaturas a un estudiante
    @PutMapping("/student_topic/assign/{idStudent}")
    public String assignStudent_topic(@RequestBody List<Student_topic> student_topic,
                                      @PathVariable int idStudent){
        try{
            return studentServiceImpl.assignStudent_topic(student_topic, idStudent).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }

    // Recibe una petición PUT para desasignar una o más asignaturas a un estudiante
    @PutMapping("/student_topic/deallocate/{idStudent}")
    public String deallocateStudent_topic(@RequestBody List<Student_topic> student_topic,
                                          @PathVariable int idStudent){
        try{
            return studentServiceImpl.deallocateStudent_topic(student_topic, idStudent).toString();
        }
        catch(EntityNotFoundException e){
            return e.getCustomError().toString();
        }
    }
}
