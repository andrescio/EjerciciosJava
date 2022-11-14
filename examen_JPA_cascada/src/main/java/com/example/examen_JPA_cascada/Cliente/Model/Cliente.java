package com.example.examen_JPA_cascada.Cliente.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCli;

    @Column(length = 100, nullable = false)
    String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }
}
