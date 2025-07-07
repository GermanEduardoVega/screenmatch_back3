package com.aluracursos.screenmatch3.controller;

import com.aluracursos.screenmatch3.dto.SerieDTO;
import com.aluracursos.screenmatch3.repository.SerieRepository;
import com.aluracursos.screenmatch3.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SerieController {

    @Autowired
    private SerieService servicio;

    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries(){
        return servicio.obtenerTodasLasSeries();
    }

    @GetMapping("/inicio")
    public String muestraMensaje(){
        return "Probando LiveReloading";
    }
}
