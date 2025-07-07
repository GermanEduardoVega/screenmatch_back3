package com.aluracursos.screenmatch3.controller;

import com.aluracursos.screenmatch3.model.Serie;
import com.aluracursos.screenmatch3.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {
    @Autowired
    private SerieRepository repository;
    @GetMapping("/series")
    public List<Serie> obtenerTodasLasSeries(){
        return repository.findAll();


    }
}
