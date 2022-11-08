package com.example.block7crudvalidation.Student.Model;

import com.example.block7crudvalidation.Profesor.Model.Profesor;
import com.example.block7crudvalidation.Student_topic.Model.Student_topic;
import com.example.block7crudvalidation.Persona.Model.Persona;
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
    Integer id_student;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;

    @NotNull
    @Column
    Integer num_hours_week;

    @Column
    String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor")
    Profesor profesor;

    @NotEmpty
    @Column
    String branch;

    @ManyToMany
    @JoinColumn(name = "id_student_topic")
    List<Student_topic> studies;

    @Override
    public String toString() {
        return "Student{\n" +
                "   id_student: " + id_student +
                ", \n   id_persona: " + persona.getId_persona() +
                ", \n   num_hours_week: " + num_hours_week +
                ", \n   comments: '" + comments + '\'' +
                ", \n   profesor: " + profesor +
                ", \n   branch: '" + branch + '\'' +
                ", \n   studies: " + studies +
                "\n}";
    }
}
