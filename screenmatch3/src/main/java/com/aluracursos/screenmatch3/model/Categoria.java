package com.aluracursos.screenmatch3.model;

public enum Categoria {
    ACCION("Action", "Acción"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime" , "Crimen");
    private String categoriaOmdb;
    private String categoriaEspaniol;

    //Constructor   //tratamiento para las categorias de nuestras series
    Categoria(String categoriaOmdb, String categoriaEspaniol){

        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspaniol = categoriaEspaniol;
    }

    public static Categoria fromString(String text) {   //realiza la conversión entre la información de OMDB y la de nuestra aplicación
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }


    public static Categoria fromEspaniol(String text) {   //verifica si el texto que esta pasando a travez de nuestro constructor del metodo es igual o puede ser ogualado a algun elemento de nuestra categoria
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEspaniol.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}


