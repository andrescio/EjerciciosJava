package com.example.block7crudvalidation.Student.Model;

import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import com.example.block7crudvalidation.Persona.Model.Persona;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    Integer id_student;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;

    @NotNull
    @Column
    @Expose
    Integer num_hours_week;

    @Column
    @Expose
    String comments;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor")
    @Expose
    Profesor profesor;

    @NotEmpty
    @Column
    @Expose
    String branch;

    @ManyToMany
    @JoinColumn(name = "id_student_topic")
    @Expose
    List<Student_topic> studies;

    @Override
    public String toString() {
        return "{\n" +
                "   id_student: " + id_student +
                ",\n    id_persona: " + persona.getId_persona() +
                ",\n    num_hours_week: " + num_hours_week +
                ",\n    comments: '" + comments + '\'' +
                ",\n    profesor: {" +
                "\n        id_profesor: " + profesor.getId_profesor() +
                ",\n        persona: " + profesor.getPersona().getId_persona() +
                ",\n        coments: " + profesor.getComments() +
                ",\n        branch: " + profesor.getBranch() +
                "\n    }" +
                ",\n    branch: '" + branch + '\'' +
                ",\n    studies: " + studies +
                "\n}";
    }
}
