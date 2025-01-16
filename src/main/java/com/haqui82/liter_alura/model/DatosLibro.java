package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<Autor> autores;

    // Constructor, getters y setters

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

    @Override
    public String toString() {
        Autor autor = autores.get(0); // Asumiendo que siempre hay al menos un autor
        return "DatosLibro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor.getNombre() + '\'' +
                ", nacimientoAutor=" + autor.getNacimientoAutor() +
                ", fallecimientoAutor=" + autor.getFallecimientoAutor() +
                '}';
    }

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
}
