package com.example.examen_JPA_cascada.CabeceraFra.Service;

import com.example.examen_JPA_cascada.CabeceraFra.Model.CabeceraFra;
import com.example.examen_JPA_cascada.LineasFra.Infraestructure.DTO.Input.LineaInputDTO;

import java.util.List;

public interface FacturaService {
    void insercionInicial();

    List<CabeceraFra> getFacturas();

    String deleteFactura(int id);

    CabeceraFra addLineaFra(LineaInputDTO lineaInputDTO);
}
