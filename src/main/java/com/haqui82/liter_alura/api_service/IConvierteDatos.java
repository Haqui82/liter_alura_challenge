package com.haqui82.liter_alura.api_service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}
