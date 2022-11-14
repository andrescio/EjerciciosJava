package com.example.examen_JPA_cascada.CabeceraFra.Model;

import com.example.examen_JPA_cascada.Cliente.Model.Cliente;
import com.example.examen_JPA_cascada.LineasFra.Model.LineasFra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class CabeceraFra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    double importeFra = 0;

    @ManyToOne
    @JoinColumn(name = "idCli")
    Cliente cli;

    @OneToMany(fetch=FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name="LINEAS_FRA")
    List<LineasFra> lineasFra;
}
