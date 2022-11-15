package com.example.block7crudvalidation.Student.Service;

import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Student.Model.Student;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;

import java.util.List;

// Interface con los métodos que tiene que implementar el StudentServiceImpl
public interface StudentService {
    Student addStudent(Student student) throws EntityNotFoundException, UnprocessableEntityException;
    Student updateStudent(Student student) throws EntityNotFoundException, UnprocessableEntityException;
    void deleteStudent(int id) throws Exception;
    String getStudentById(int id, String outputType) throws Exception;
    List<String> findAllStudents(String outputType);
    Student assignStudent_topic(List<Student_topic> student_topic, int idStudent);
    Student deallocateStudent_topic(List<Student_topic> student_topic, int idStudent);
}
