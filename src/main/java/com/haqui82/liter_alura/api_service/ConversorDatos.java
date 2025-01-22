package com.haqui82.liter_alura.api_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDatos implements IConvierteDatos {

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertirAJson(Object objeto) {
        try { return objectMapper.writeValueAsString(objeto); }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e); } }

}
