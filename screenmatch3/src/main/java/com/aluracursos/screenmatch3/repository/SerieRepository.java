package com.aluracursos.screenmatch3.repository;

import com.aluracursos.screenmatch3.dto.EpisodioDTO;
import com.aluracursos.screenmatch3.model.Categoria;
import com.aluracursos.screenmatch3.model.Episodio;
import com.aluracursos.screenmatch3.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
    //Query Methods
    @Query("SELECT s FROM Serie s WHERE LOWER(s.titulo) LIKE LOWER(concat('%', :nombreSerie, '%'))")                        //
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);

    @Query("SELECT s FROM Serie s ORDER BY s.evaluacion DESC LIMIT 5")
    List<Serie> findTop5ByOrderByEvaluacionDesc();
    @Query("SELECT s FROM Serie s WHERE s.genero = :categoria")
    List<Serie> findByGenero(Categoria categoria);

    //List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totalTemporadas, Double evaluacion);


    @Query("SELECT s FROM Serie s WHERE s.totalDeTemporadas <= :totalTemporadas AND s.evaluacion >= :evaluacion")
    List<Serie> seriesPorTemporadaYEvaluacion(int totalTemporadas, Double evaluacion);

    @Query("SELECT DISTINCT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Episodio> episodiosPorNombre(String nombreEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5 ")
    List<Episodio> top5Episodios(Serie serie);

    @Query("SELECT s FROM Serie s " + "JOIN s.episodios e " + "GROUP BY s " + "ORDER BY MAX(e.fechaDeLanzamiento) DESC LIMIT 5")
    List<Serie> lanzamientosMasRecientes();
    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numeroTemporada")
    List<Episodio> obtenerTemporadasPorNumero(Long id, Long numeroTemporada);
}
