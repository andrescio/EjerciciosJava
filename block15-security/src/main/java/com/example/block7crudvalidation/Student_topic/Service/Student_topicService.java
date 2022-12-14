package com.example.block7crudvalidation.Student_topic.Service;

import com.example.block7crudvalidation.Student_topic.Model.Student_topic;

import java.util.List;

// Interface con los métodos que tiene que implementar el Student_topicServiceImpl
public interface Student_topicService {
    Student_topic addStudent_topic(Student_topic student_topic) throws Exception;
    Student_topic updateStudent_topic(Student_topic student_topic) throws Exception;
    void deleteStudent_topic(int id) throws Exception;
    String getStudent_topic(int id) throws Exception;
    List<Student_topic> findAllStudent_topic();
    List<Student_topic> getTopicStudent(int id);
}
