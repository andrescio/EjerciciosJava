package com.example.examen_JPA_cascada.LineasFra.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class LineasFra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idFra;

    @Column(nullable = false)
    String proNomb;

    @Column
    double cantidad;

    @Column
    double precio;

    public LineasFra(String proNomb, double cantidad, double precio) {
        this.proNomb = proNomb;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
