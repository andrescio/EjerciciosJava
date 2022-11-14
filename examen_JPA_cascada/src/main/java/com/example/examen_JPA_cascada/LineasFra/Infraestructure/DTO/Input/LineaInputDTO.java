package com.example.examen_JPA_cascada.LineasFra.Infraestructure.DTO.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineaInputDTO {

    String producto;

    double cantidad;

    double importe;

    int idFra;
}
