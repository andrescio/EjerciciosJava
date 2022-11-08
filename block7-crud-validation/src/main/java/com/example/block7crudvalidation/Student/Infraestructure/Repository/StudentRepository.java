package com.example.block7crudvalidation.Student.Infraestructure.Repository;

import com.example.block7crudvalidation.Student.Model.Student;
import org.springframework.data.repository.CrudRepository;

// Interface con los métodos CRUD por defecto de CrudRepository.
public interface StudentRepository extends CrudRepository<Student, Integer> {
}
