package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "resultados_api")
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiData(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false) // Clave primaria no debe permitir nulos
        Long id_respuestas_api,

        @OneToMany(mappedBy = "response", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonAlias("results")
        List<Libros> resultados,

        @Column(nullable = true)
        @JsonAlias("count")
        Long cantidad,

        @Column(nullable = true)
        @JsonAlias("next")
        String siguiente,

        @Column(nullable = true)
        @JsonAlias("previous")
        String anterior
) {}
