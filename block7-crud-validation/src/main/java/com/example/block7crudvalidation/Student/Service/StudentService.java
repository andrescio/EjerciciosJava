package com.example.block7crudvalidation.Student.Service;

import com.example.block7crudvalidation.Student.Model.Student;

import java.util.List;

// Interface con los métodos que tiene que implementar el StudentServiceImpl
public interface StudentService {
    Student addStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudent(int id) throws Exception;
    String getStudentById(int id, String outputType) throws Exception;
    List<String> findAllStudents(String outputType);
}
