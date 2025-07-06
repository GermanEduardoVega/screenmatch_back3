package com.aluracursos.screenmatch3.model;

import com.aluracursos.screenmatch3.service.ConsultaGemini;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Al momento de queres tener esta clase serie como una clase que almacene los valores en una base de datos voy a utilizar las anotaciones
 * que me brinda JPA para dotar esta clase como una Entidad y que esta se comporte guardandome los datos en una BD
 */
@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Integer totalDeTemporadas;
    private Double evaluacion;
    private String poster;
    @Enumerated(EnumType.STRING)    //ORDINAL ocupa la posicion y si en el futuro se cambia traeria problemas
    private Categoria genero;
    private String actores;
    private String sinopsis;
    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Episodio> episodios;   //una Serie contiene una lista de episodios que se mapea

    //CONSTRUCTOR PREDETERMINADO
    public Serie() {
    }

    //CONSTRUCTOR SOBRECARGADO
    public Serie(DatosSerie datosSerie){    //referencia datosSerie para conseguir crear una serie del record
        this.titulo = datosSerie.titulo();
        this.totalDeTemporadas = datosSerie.totalDeTemporadas();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerie.evaluacion())).orElse(0);     //transformacion de datos series de string al double
        this.poster = datosSerie.poster();
        //this.genero = datosSerie.genero();
        this.genero = Categoria.fromString(datosSerie.genero()
                .split(",")[0]       // realiza la separacion , punto de quiebre la , usa el primero
                .trim());                  //no traiga ningun valor vacio

        this.actores = datosSerie.actores();
        //this.sinopsis = datosSerie.sinopsis();  //sin IA
        //this.sinopsis = ConsultaChatGPT .obtenerTraduccion(datosSerie.sinopsis()) ;   //ChatGPT
        this.sinopsis = ConsultaGemini.obtenerTraduccion(datosSerie.sinopsis());        //Gemini
    }

    //Gets y Sets


    public Long getId() { 
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public void setTotalDeTemporadas(Integer totalDeTemporadas) {
        this.totalDeTemporadas = totalDeTemporadas;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }


    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {

        episodios.forEach(episodio -> episodio.setSerie(this));
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return  "genero=" + genero +
                ", titulo='" + titulo + '\'' +
                ", totalDeTemporadas=" + totalDeTemporadas +
                ", evaluacion=" + evaluacion +
                ", poster='" + poster + '\'' +
                ", actores='" + actores + '\'' +
                ", sinopsis='" + sinopsis + '\'' ;
                //", episodios='" + episodios + '\'';       //mostramos los episodios
    }
}
