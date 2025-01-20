package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "datosResponses")
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResponses(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonAlias("id") Integer id,

        @ElementCollection
        @JsonAlias("results") List<DatosLibros> resultados,

        @JsonAlias("count") Integer cantidad,

        @JsonAlias("next") String siguiente,

        @JsonAlias("previous") String anterior
) {}
