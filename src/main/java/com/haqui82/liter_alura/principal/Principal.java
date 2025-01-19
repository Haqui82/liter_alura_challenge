package com.haqui82.liter_alura.principal;

import com.haqui82.liter_alura.api_service.ConsumoAPI;
import com.haqui82.liter_alura.api_service.ConversorDatos;
import com.haqui82.liter_alura.model.DatosLibros;
import com.haqui82.liter_alura.model.DatosResponses;
import com.haqui82.liter_alura.model.DatosAutor;
import com.haqui82.liter_alura.model.DatosURLs;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConversorDatos conversorDatos = new ConversorDatos();

    public void muestraMenu() throws InterruptedException {

        // Variable para almacenar la elección del usuario
        int option = 0;
        // Menú de opciones
        do {
            System.out.println("Hola amantes de la literatura");
            System.out.println("\t\t\tMenú de Opciones:");
            System.out.println("Para consultar libros escoge una de las opciones:");
            System.out.println("1. Buscar libro por titulo en la API de Gutendex");
            System.out.println("2. Buscar libro por ID en la API de Gutendex");
            System.out.println("3. Listar el top 10 de mayores descargas en la API de Gutendex");
            System.out.println("4. Listar libros registrados en la base de datos");
            System.out.println("5. Listar autores registrados en la base de datos");
            System.out.println("6. Listar autores registrados en la base de datos; vivos en un determinado año");
            System.out.println("7. Listar libros registrados en la bases de datos, por determinado idioma");
            System.out.println("8. Salir");

            System.out.print("Por favor, elige una opción: ");
            // Leer la elección del usuario con control de errores
            try {
                option = Integer.parseInt(teclado.nextLine());
                var baseURL = "https://gutendex.com/books/";

                // Procesar la elección del usuario
                switch (option) {
                    case 1:
                        System.out.println("Has elegido la Opción 1. Buscar libro por titulo en la API de Gutendex");
                        System.out.println("Escribe el titulo o palabras en él a buscar");
                        var busqueda = teclado.nextLine();
                        var busquedaURL = busqueda.replace(" ", "%20");
                        // realizar consumo de datos de la API
                        var json = consumoApi.obtenerDatos(baseURL + "?search=" + busquedaURL);
                        System.out.println(json);
                        //transformar respuesta en objeto
                        var datos = conversorDatos.obtenerDatos(json, DatosResponses.class);
                        System.out.println(datos);
                        //Tomar las respuesta (datos)>>acceder al atributo resultados (lista de tipo DatosLibros)
                        Optional<DatosLibros> resultadoBusqueda = datos.resultados().stream()
                                .filter(l -> l.titulo().toUpperCase().contains(busqueda.toUpperCase()))
                                .findFirst();
                        if(resultadoBusqueda.isPresent()){
                            System.out.println("Total de registros encontrados: "+ datos.cantidad());
                            //System.out.println("libro encontrado: ");
                            System.out.println(resultadoBusqueda.get().imprimeDatosLibro(datos));
                        }else {
                            System.out.println("Libro no encontrado");
                        }
                        Thread.sleep(3000);
                    break;

                    case 2:
                        System.out.println("Has elegido la Opción 2. buscar libros por palabras clave");
                        System.out.println("Escribe las palabras clave a buscar");
                        var busqueda2 = teclado.nextLine();
                        var busquedaURL2 = busqueda2.replace(" ", "%20");
                        // realizar consumo de datos de la API
                        var json2 = consumoApi.obtenerDatos(baseURL + "?search=" + busquedaURL2);
                        System.out.println(json2);
                        //transformar respuesta en objeto
                        var datos2 = conversorDatos.obtenerDatos(json2, DatosResponses.class);
                        System.out.println(datos2);
                        // Formato de impresión
                            // contador para Numeración de líneas
                       AtomicInteger contador = new AtomicInteger(1);

                        System.out.println("Resultados de la búsqueda:");
                        datos2.resultados().stream()
                            .forEach(libro -> {
                                System.out.println(
                                    "\t" + contador.getAndIncrement() +
                                    "ID Gutendex: " + libro.id() + "\n" +
                                    "\tTitulo:\t" + libro.titulo() + "\n" +
                                    "\tAutor:\t" + libro.autores().stream()
                                    .map(DatosAutor::nombre)
                                    .collect(Collectors.joining(", ")) + "\n"
                                    //"\tLink de descarga:\t" + enlaceDescarga + "\n"
                                );
                            });
                    break;
                    case 3:
                        System.out.println("Has elegido la Opción 3. Buscar libro por ID en la API de Gutendex");
//                        System.out.println("Escribe el numero de ID del libro a consultar (1 al 75101)");
//                        var idLibro = teclado.nextInt();
//                        if (idLibro < 75102 ) {
//                            var json2 = consumoApi.obtenerDatos("https://gutendex.com/books/" + idLibro + "/");
//                            var datos2 = conversorDatos.obtenerDatos(json2, DatosLibros.class);
//                            System.out.println(datos2);
//                            //---------------------------
//                            // Formato de impresión
//                            System.out.println(
//                                "\t" + "ID Gutendex:\t" + datos2.id() + "\n" +
//                                "\tTitulo:\t" + datos2.titulo() + "\n" +
//                                "\tAutor:\t" + datos2.autores() + "\n"
//                                //"\tLink de descarga:\t" + enlaceDescarga + "\n"
//                            );
//                            //---------------------------
//                            Thread.sleep(3000);
//                        } else {
//                            System.out.println("Ingreso no valido");
//                            Thread.sleep(3000);
//                        }
                    break;

                    case 4:
                        System.out.println("Top 10 de los libros más descargados");
//                        var json3 = consumoApi.obtenerDatos(baseURL);
//                        var datos3 = conversorDatos.obtenerDatos(json3, DatosResponses.class);
//                        System.out.println(datos3);
//// Numeración de líneas
//                        AtomicInteger contador2 = new AtomicInteger(1);
//                        System.out.println("Resultados de la búsqueda:");
//                        datos3.resultados().stream()
//                                .sorted(Comparator.comparing(DatosLibros::numeroDescargas).reversed())
//                                .limit(10)
//                                .forEach(libro -> {
//                                    // Obtener el enlace de descarga
//                                    //var enlaceDescarga = obtenerEnlaceDescarga(libro.id());
//
//                                    // Formato de impresión
//                                    System.out.println(
//                                            "\t" + contador2.getAndIncrement() + ". ID Gutendex: " + libro.id() + "\n" +
//                                                    "\tTitulo:\t" + libro.titulo() + "\n" +
//                                                    "\tAutor:\t" + libro.autores().stream()
//                                                    .map(DatosAutor::nombre)
//                                                    .collect(Collectors.joining(", ")) + "\n"
//                                                    //"\tLink de descarga:\t" + enlaceDescarga + "\n"
//                                    );
//                                });
//
//                        Thread.sleep(3000);

                        break;

                    case 5:
                        System.out.println("Has elegido la Opción 5. Lista de libros registrados en la base de datos:");

//
//                        System.out.println("Escribe el numero de ID del libro a consultar (1 al 75101)");
//                        var idLibro4 = teclado.nextInt();
//                        if (idLibro4 < 75102 ) {
//                            var json4 = consumoApi.obtenerDatos("https://gutendex.com/books/" + idLibro4 + "/");
//                            var datos4 = conversorDatos.obtenerDatos(json4, DatosURLs.class);
//                            System.out.println(datos4.web());
//                            System.out.println(datos4.stream());
//                        }
                        break;

                    case 6:
                        System.out.println("Has elegido la Opción 6. Lista de autores registrados en la base de datos:");
                        //invocacion(sufix1, sufix3);
                        break;
                    case 7:
                        System.out.println("Has elegido la Opción 7. Lista de autores registrados en la base de datos; vivos en un determinado año");
                        //invocacion(sufix3, sufix1);
                        break;
                    case 8:
                        System.out.println("Has elegido la Opción 8. Listar libros registrados en la bases de datos, por determinado idioma");
                        //invocacion(sufix1, sufix4);
                        break;
                    case 9:
                        System.out.println("Has elegido la Opción 9. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida, por favor intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Inténtelo de nuevo.");
                teclado.nextLine(); // Limpiar el buffer del escáner
                Thread.sleep(2000);
                            }
        } while (option != 9);
        // Cerrar el scanner
        teclado.close();
    }
}
