package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("name")String autor,
        @JsonAlias("birth_year")Integer nacimientoAutor,
        @JsonAlias("death_year")Integer fallecimientoAutor) {
}
