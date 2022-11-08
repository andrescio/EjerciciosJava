package com.example.block7crudvalidation.Student_topic.Infraestructure.Controller;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import com.example.block7crudvalidation.Student_topic.Service.Student_topicServiceImpl;
import com.example.block7crudvalidation.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
