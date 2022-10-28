package com.example.block7crud.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Modelo de una persona. Getters y setters generados de forma automática con Lombok
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String name;

    @Column
    int age;

    @Column
    String poblacion;
}
