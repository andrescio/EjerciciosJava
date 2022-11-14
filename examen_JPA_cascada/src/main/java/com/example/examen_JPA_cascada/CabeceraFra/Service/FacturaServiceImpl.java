package com.example.examen_JPA_cascada.CabeceraFra.Service;

import com.example.examen_JPA_cascada.CabeceraFra.Infraestructure.Repository.CabeceraFraRepository;
import com.example.examen_JPA_cascada.CabeceraFra.Model.CabeceraFra;
import com.example.examen_JPA_cascada.Cliente.Infraestructure.Repository.ClienteRepository;
import com.example.examen_JPA_cascada.Cliente.Model.Cliente;
import com.example.examen_JPA_cascada.Excepciones.EntityNotFoundException;
import com.example.examen_JPA_cascada.LineasFra.Infraestructure.DTO.Input.LineaInputDTO;
import com.example.examen_JPA_cascada.LineasFra.Infraestructure.Repository.LineasFraRepository;
import com.example.examen_JPA_cascada.LineasFra.Model.LineasFra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService{

    @Autowired
    CabeceraFraRepository cabeceraFraRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    LineasFraRepository lineasFraRepository;

    // Método que crea el Cliente, las Lineas y la Cabecera
    @Override
    public void insercionInicial() {
        // Nuevo cliente
        Cliente cliente = new Cliente("Manolo");
        clienteRepository.save(cliente);

        // Nueva CabeceraFra
        CabeceraFra cabeceraFra = new CabeceraFra();
        cabeceraFra.setCli(cliente);

        List<LineasFra> listaLineasFra = new ArrayList<>();

        // Nuevas lineasFra
        LineasFra lineasFra1 = new LineasFra("Linea1", 15, 20);
        LineasFra lineasFra2 = new LineasFra("Linea2", 30, 40);

        // Se añaden las líneas a la lista de líneas
        listaLineasFra.add(lineasFra1);
        listaLineasFra.add(lineasFra2);

        // Se le asignan las lineas a la cabecera y se guarda la cabecera en la BBDD, lo que también guardará las líneas
        cabeceraFra.setLineasFra(listaLineasFra);
        cabeceraFraRepository.save(cabeceraFra);
    }

    // Método que busca todas las facturas existentes
    @Override
    public List<CabeceraFra> getFacturas() {
        return Streamable.of(cabeceraFraRepository.findAll()).toList();
    }

    // Método que elimina la factura
    @Override
    public String deleteFactura(int id) {
        // Busca la factura con ese ID. Si no existe, lanza la excepción de que no se encontró.
        Optional<CabeceraFra> factura = cabeceraFraRepository.findById(id);
        if(factura.isEmpty()){
            throw new EntityNotFoundException();
        }
        // Si existe, la borra
        cabeceraFraRepository.delete(factura.get());
        return "Borrado correctamente";
    }

    // Método que añade una línea a una factura ya existente
    @Override
    public CabeceraFra addLineaFra(LineaInputDTO lineaInputDTO) {
        // Busca la factura con ese ID. Si no existe, lanza la excepción de que no se encontró
        Optional<CabeceraFra> factura = cabeceraFraRepository.findById(lineaInputDTO.getIdFra());
        if(factura.isEmpty()){
            throw new EntityNotFoundException();
        }
        // Si existe crea una lista de Lineas con las actuales de la factura más la nueva, añade en la factura y
        // se guarda en la BBDD
        List<LineasFra> listaLineas = factura.get().getLineasFra();
        LineasFra linea = new LineasFra(lineaInputDTO.getProducto(),
                                        lineaInputDTO.getCantidad(),
                                        lineaInputDTO.getImporte());
        listaLineas.add(linea);
        factura.get().setLineasFra(listaLineas);
        return cabeceraFraRepository.save(factura.get());
    }
}
