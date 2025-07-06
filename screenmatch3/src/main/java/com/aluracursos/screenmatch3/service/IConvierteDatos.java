package com.aluracursos.screenmatch3.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
