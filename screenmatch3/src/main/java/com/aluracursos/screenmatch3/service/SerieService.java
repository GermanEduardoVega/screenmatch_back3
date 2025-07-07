package com.aluracursos.screenmatch3.service;

import com.aluracursos.screenmatch3.dto.SerieDTO;
import com.aluracursos.screenmatch3.model.Serie;
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
        return convierteDatos(repository.findAll());
    }

    public List<SerieDTO> obtenerTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());

    }


    public List<SerieDTO> obtenerLanzamientosMasRecientes(){
        return convierteDatos(repository.lanzamientosMasRecientes());
    }

    public List<SerieDTO> convierteDatos(List<Serie> series){            //transformacion de mi Serie a SerieDTO
        return series.stream()
                    .map(serie -> new SerieDTO(
                             serie.getTitulo()
                            ,serie.getTotalDeTemporadas()
                            ,serie.getEvaluacion()
                            ,serie.getPoster()
                            ,serie.getGenero()
                            ,serie.getActores()
                            ,serie.getSinopsis()))
                    .collect(Collectors.toList());                      //cada vez que yo pase una serie va a haber una cnversion de datos
        }                                                               //para convertir eso una lista pero de tipo de datos SerieDTO



}

