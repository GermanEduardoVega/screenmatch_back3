package com.aluracursos.screenmatch3.service;

import com.aluracursos.screenmatch3.dto.SerieDTO;
import com.aluracursos.screenmatch3.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;
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
}   
