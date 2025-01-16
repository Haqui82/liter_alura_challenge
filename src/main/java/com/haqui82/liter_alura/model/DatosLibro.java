package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("name")String autor,
        @JsonAlias("birth_year")Integer nacimientoAutor,
        @JsonAlias("death_year")Integer fallecimientoAutor) {

    @Override
    public String titulo() {
        return titulo;
    }

    @Override
    public String autor() {
        return autor;
    }

    @Override
    public Integer nacimientoAutor() {
        return nacimientoAutor;
    }

    @Override
    public Integer fallecimientoAutor() {
        return fallecimientoAutor;
    }

    public DatosLibro {
    }

    @Override
    public String toString() {
        return "DatosLibro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", nacimientoAutor=" + nacimientoAutor +
                ", fallecimientoAutor=" + fallecimientoAutor +
                '}';
    }
}
