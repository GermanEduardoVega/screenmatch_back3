package com.aluracursos.screenmatch3.controller;

import com.aluracursos.screenmatch3.dto.SerieDTO;
import com.aluracursos.screenmatch3.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SerieController {
    @Autowired
    private SerieRepository repository;
    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries(){
        return repository.findAll().stream()        //transformacion de mi Serie a SerieDTO
                .map(serie -> new SerieDTO(serie.getTitulo()
                                            ,serie.getTotalDeTemporadas()
                                            ,serie.getEvaluacion()
                                            ,serie.getPoster()
                                            ,serie.getGenero()
                                            ,serie.getActores()
                                            ,serie.getSinopsis()))
                .collect(Collectors.toList());      //para convertir eso una lista pero de tipo de datos SerieDTO
    }

    @GetMapping("/inicio")
    public String muestraMensaje(){
        return "Probando LiveReloading";
    }
}
