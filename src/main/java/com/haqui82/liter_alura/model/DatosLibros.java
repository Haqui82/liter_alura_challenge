package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DatosLibros(

    @JsonAlias("id") Integer id,
    @JsonAlias("title")
    String titulo,
    @JsonAlias("authors")
    List<DatosAutor> autores,
    @JsonAlias("formats")
    Map<String, String> urls,
    @JsonAlias("languages")
    List<String> idiomas,
    @JsonAlias("download_count")
    Double numeroDescargas
) {
    public DatosLibros imprimeDatosLibro(DatosResponses d) {
        // Formato de impresión
        // contador para Numeración de líneas
        AtomicInteger contador = new AtomicInteger(1);

        System.out.println("Resultados de la búsqueda:");
        DatosLibros resultado = null;
        Optional<DatosLibros> resultadoBusqueda = d.resultados().stream().findFirst();

        if (resultadoBusqueda.isPresent()) {
            resultado = resultadoBusqueda.get();
            d.resultados().stream()
                    .forEach(libro -> {
                        System.out.println(
                                "\t" + contador.getAndIncrement() +
                                " ID Gutendex:\t " + libro.id() + "\n" +
                                "\tTitulo: \t" + libro.titulo() + "\n" +
                                "\tAutor: \t" + libro.autores()+ "\n" +
                                "\tlink web: \t" + libro.urls.get("text/html") + "\n" +
                                "\tlink de descarga \t"+ libro.urls.get("application/octet-stream") +"\n"                        );

                    });
        } else {
            System.out.println("No se encontraron resultados.");
        }

        return resultado; // Devolver el primer libro encontrado o null si no hay resultados
    }

}



//   public String imprimeDatosLibro() {
//        return "\tID Gutendex: " + id + "\n" +
//                "\tTitulo:\t " + titulo + "\n" +
//                "\tAutor:\n\t " + autores+ "\n" +
//                "\tIdiomas:\t " + String.join(", ", idiomas) + "\n" +
//                "\tNúmero de Descargas:\t " + numeroDescargas + "\n" +
//                "\tURLs: \n\t" + urls +"\n";
//    };

