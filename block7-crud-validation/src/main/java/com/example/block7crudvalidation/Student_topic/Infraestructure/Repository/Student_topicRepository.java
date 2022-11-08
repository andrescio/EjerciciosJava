package com.example.block7crudvalidation.Student_topic.Infraestructure.Repository;

import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import org.springframework.data.repository.CrudRepository;

// Interface con los métodos CRUD por defecto de CrudRepository.
public interface Student_topicRepository extends CrudRepository<Student_topic, Integer> {
}
