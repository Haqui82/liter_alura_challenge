package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Embeddable
public record Autor(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false)
        Long id_autor,

        @JsonAlias("name")
        String nombre,

        @JsonAlias("birth_year")
        Integer nacimientoAutor,

        @JsonAlias("death_year")
        Integer fallecimientoAutor
) {}
