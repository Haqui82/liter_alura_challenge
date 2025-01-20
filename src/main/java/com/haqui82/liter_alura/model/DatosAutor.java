package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;

@JsonIgnoreProperties (ignoreUnknown = true)
@Embeddable
public record DatosAutor(
        @JsonAlias("name")
         String nombre,

        @JsonAlias("birth_year")
        Integer nacimientoAutor,

        @JsonAlias("death_year")
        Integer fallecimientoAutor
) {
}
