package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "Libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public record Libros(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false) // Clave primaria no debe permitir nulos
        Long id_libros,

        @Column(unique = true)
        @JsonAlias("title")
        String titulo,

        @ElementCollection
        @JsonAlias("authors")
        List<Autor> autores,

        @ElementCollection
        @JsonAlias("formats")
        Map<String, String> formats_urls,

        @ElementCollection
        @JsonAlias("languages")
        List<String> idiomas,

        @JsonAlias("download_count")
        Double numeroDescargas,

        @ManyToOne
        @JoinColumn(name = "response_id")
        @JsonAlias("id")
        ApiData response
) {

    public Libros imprimeDatosLibro(ApiData d) throws InterruptedException {
        // Formato de impresión
        // Contador para numeración de líneas
        AtomicInteger contador = new AtomicInteger(1);

        System.out.println("Resultados de la búsqueda:");

        // Utilizar un valor predeterminado en lugar de null
        Libros resultado = d.resultados().stream().findFirst().orElse(new Libros(
                0L, // ID predeterminado (valor inicial)
                "Título no disponible", // Título predeterminado
                new ArrayList<>(), // Lista vacía de autores
                new HashMap<>(), // Mapa vacío de URLs
                new ArrayList<>(), // Lista vacía de temas
                0.0, // Número de descargas predeterminado
                d // Asociación con ApiData
        ));

        d.resultados().forEach(libro -> {
            System.out.println(
                    "\t" + contador.getAndIncrement() +
                            " ID Gutendex:\t " + libro.id_libros + "\n" +
                            "\tTitulo: \t" + libro.titulo() + "\n" +
                            "\tAutor: \t" + libro.autores() + "\n" +
                            "\tlink web: \t" + libro.formats_urls().get("text/html") + "\n" +
                            "\tlink de descarga \t" + libro.formats_urls().get("application/octet-stream") + "\n"
            );
        });

        if (d.resultados().isEmpty()) {
            System.out.println("No se encontraron resultados.");
        }

        Thread.sleep(3000);

        return resultado; // Devolver el primer libro encontrado o un valor predeterminado si no hay resultados
    }
}
