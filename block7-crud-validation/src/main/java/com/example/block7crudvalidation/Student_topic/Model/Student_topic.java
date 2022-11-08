package com.example.block7crudvalidation.Student_topic.Model;

import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student.Model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Student_topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_student_topic;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor")
    Profesor profesor;

    //@NotNull
   // @ManyToMany(mappedBy = "studies")
    //@JoinColumn(name = "id_student")
    //List<Student> students;

    @NotEmpty
    @Column
    String course;

    @Column
    String comment;

    @NotNull
    @Column
    Date initial_date;

    @Column
    Date finish_date;

}
