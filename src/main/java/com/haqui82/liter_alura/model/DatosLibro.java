package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {
    // Atributos
    @JsonAlias("title")
    private String titulo;
    @JsonAlias("authors")
    private List<Autor> autores;
    @JsonAlias("languages")
    private List<String> idiomas;
    @JsonAlias("download_count")
    private Integer numeroDescargas;

    // Constructores
    public DatosLibro() {
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    // Clase anidada para el manejo de datos la lista "autores"
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Autor {

        @JsonAlias("name")
        private String nombre;

        @JsonAlias("birth_year")
        private Integer nacimientoAutor;

        @JsonAlias("death_year")
        private Integer fallecimientoAutor;

        // Constructor, getters y setters

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Integer getNacimientoAutor() {
            return nacimientoAutor;
        }

        public void setNacimientoAutor(Integer nacimientoAutor) {
            this.nacimientoAutor = nacimientoAutor;
        }

        public Integer getFallecimientoAutor() {
            return fallecimientoAutor;
        }

        public void setFallecimientoAutor(Integer fallecimientoAutor) {
            this.fallecimientoAutor = fallecimientoAutor;
        }
    }

    // Clase anidada para el manejo de datos en la lista "idiomas"
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Idioma {

        @JsonAlias("languages")
        private String idioma;

        // Getter
        public String getIdioma() {
            return idioma;
        }

        public void setIdioma(String idioma) {
            this.idioma = idioma;
        }
    }

    @Override
    public String toString() {
        Autor autor = autores.get(0); // Asumiendo que siempre hay al menos un autor
        String idioma = idiomas.get(0); // Usando get(0) para listas
        return "DatosLibro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor.getNombre() + '\'' +
                ", nacimientoAutor=" + autor.getNacimientoAutor() +
                ", fallecimientoAutor=" + autor.getFallecimientoAutor() +
                ", idioma=" + idioma +
                ", numeroDescargas=" + numeroDescargas +
                '}';
    }
}
