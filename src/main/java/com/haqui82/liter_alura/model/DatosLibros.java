package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DatosLibros(

    @JsonAlias("id") Integer id,
    @JsonAlias("title")
    String titulo,
    @JsonAlias("authors")
    List<DatosAutor> autores,
    @JsonAlias("formats")
    Map<String, String> urLs,
    @JsonAlias("languages")
    List<String> idiomas,
    @JsonAlias("download_count")
    Double numeroDescargas
) {
}
