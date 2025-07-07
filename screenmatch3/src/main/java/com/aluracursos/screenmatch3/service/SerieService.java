package com.aluracursos.screenmatch3.service;

import com.aluracursos.screenmatch3.dto.EpisodioDTO;
import com.aluracursos.screenmatch3.dto.SerieDTO;
import com.aluracursos.screenmatch3.model.Categoria;
import com.aluracursos.screenmatch3.model.Serie;
import com.aluracursos.screenmatch3.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public SerieDTO obtenerPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(
                     s.getId()
                    ,s.getTitulo()
                    ,s.getTotalDeTemporadas()
                    ,s.getEvaluacion()
                    ,s.getPoster()
                    ,s.getGenero()
                    ,s.getActores()
                    ,s.getSinopsis());
        }
        return null;
    }

    public List<EpisodioDTO> obtenerTodasLasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(episodio -> new EpisodioDTO(episodio.getTemporada()
                                                    ,episodio.getTitulo()
                                                    ,episodio.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obtenerTemporadasPorNumero(Long id, Long numeroTemporada) {
        return repository.obtenerTemporadasPorNumero(id,numeroTemporada).stream()
                .map(episodio -> new EpisodioDTO(episodio.getTemporada()
                                                ,episodio.getTitulo()
                                                ,episodio.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }


    public List<SerieDTO> obtenerSeriesPorCategorias(String nombreGenero) {
        Categoria categoria = Categoria.fromEspaniol(nombreGenero);
        return convierteDatos(repository.findByGenero(categoria));
    }

    public List<SerieDTO> convierteDatos(List<Serie> series){            //transformacion de mi Serie a SerieDTO
        return series.stream()
                    .map(serie -> new SerieDTO(
                             serie.getId()
                            ,serie.getTitulo()
                            ,serie.getTotalDeTemporadas()
                            ,serie.getEvaluacion()
                            ,serie.getPoster()
                            ,serie.getGenero()
                            ,serie.getActores()
                            ,serie.getSinopsis()))
                    .collect(Collectors.toList());                      //cada vez que yo pase una serie va a haber una cnversion de datos
        }                                                               //para convertir eso una lista pero de tipo de datos SerieDTO


}

