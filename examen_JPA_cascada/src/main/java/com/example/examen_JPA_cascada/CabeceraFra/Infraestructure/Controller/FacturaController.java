package com.example.examen_JPA_cascada.CabeceraFra.Infraestructure.Controller;

import com.example.examen_JPA_cascada.CabeceraFra.Model.CabeceraFra;
import com.example.examen_JPA_cascada.CabeceraFra.Service.FacturaServiceImpl;
import com.example.examen_JPA_cascada.LineasFra.Infraestructure.DTO.Input.LineaInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    FacturaServiceImpl facturaServiceImpl;

    // Método que recibe una petición GET y devuelve las facturas que existan
    @GetMapping
    public List<CabeceraFra> getFacturas(){
        return facturaServiceImpl.getFacturas();
    }

    // Método que recibe una petición delete y elimina las facturas que coincidan con él
    @DeleteMapping("/{idFra}")
    public String deleteFacturas(@PathVariable int idFra){
        return facturaServiceImpl.deleteFactura(idFra);
    }

    // Método que recibe una petición PUT y añade las líneas a una factura concreta
    @PutMapping("/linea/{idFra}")
    public CabeceraFra addLineaFra(@RequestBody LineaInputDTO lineaInputDTO,
                        @PathVariable int idFra){
        lineaInputDTO.setIdFra(idFra);
        return facturaServiceImpl.addLineaFra(lineaInputDTO);
    }
}
