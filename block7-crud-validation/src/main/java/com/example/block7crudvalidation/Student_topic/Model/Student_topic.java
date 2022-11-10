package com.example.block7crudvalidation.Student_topic.Model;

import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student.Model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    Profesor profesor;

    //@NotNull
   // @ManyToMany(mappedBy = "studies")
    //@JoinColumn(name = "id_student")
    //List<Student> students;

    @NotEmpty
    @Column
    @Expose
    String course;

    @Column
    @Expose
    String comment;

    @NotNull
    @Column
    @Expose
    Date initial_date;

    @Column
    @Expose
    Date finish_date;

    @Override
    public String toString() {
        return "Student_topic{" +
                "\n    id_student_topic: " + id_student_topic +
                ",\n    profesor: {" +
                "\n        id_profesor: " + profesor.getId_profesor() +
                ",\n        id_persona: " + profesor.getPersona().getId_persona() +
                ",\n        comments: '" + profesor.getComments() + '\'' +
                ",\n        branch: '" + profesor.getBranch() + '\'' +
                "\n    }" +
                ",\n    course: '" + course + '\'' +
                ",\n    comment: '" + comment + '\'' +
                ",\n    initial_date: " + initial_date +
                ",\n    finish_date: " + finish_date +
                "\n}";
    }
}
