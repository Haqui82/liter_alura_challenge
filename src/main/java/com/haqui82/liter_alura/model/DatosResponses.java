package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResponses(
    @JsonAlias("results") List<DatosLibros> resultados,// NO TOCAR. el unico confirmado por ahora.
    @JsonAlias("count") Integer cantidad,
    @JsonAlias("next") String siguiente,
    @JsonAlias("previous") String anterior
) {

}
