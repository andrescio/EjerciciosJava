package com.example.block7crudvalidation.Profesor.Model;

import com.example.block7crudvalidation.Persona.Model.Persona;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    Integer id_profesor;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;

    @Column
    @Expose
    String comments;

    @NotEmpty
    @Column
    @Expose
    String branch;

    @Override
    public String toString() {
        return "{" +
                "\n    id_profesor: " + id_profesor +
                ",\n    id_persona: " + persona.getId_persona() +
                ",\n    comments: '" + comments + '\'' +
                ",\n    branch: '" + branch + '\'' +
                "\n}";
    }
}
