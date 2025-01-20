package com.haqui82.liter_alura.principal;

import com.haqui82.liter_alura.api_service.ConsumoAPI;
import com.haqui82.liter_alura.api_service.ConversorDatos;
import com.haqui82.liter_alura.model.DatosLibros;
import com.haqui82.liter_alura.model.DatosResponses;
import com.haqui82.liter_alura.model.DatosAutor;
import com.haqui82.liter_alura.model.DatosURLs;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            System.out.println("Para consultar libros escoge una de las opciones:\n");
            System.out.println("1. Buscar libro por palabras clave (titulo, autor) en la API de Gutendex");
            System.out.println("2. Buscar libro por ID en la API de Gutendex");
            System.out.println("3. Listar el top 10 de mayores descargas en la API de Gutendex");
            System.out.println("5. Listar libros registrados en la base de datos");
            System.out.println("6. Listar autores registrados en la base de datos");
            System.out.println("7. Listar autores registrados en la base de datos; vivos en un determinado año");
            System.out.println("8. Listar libros registrados en la bases de datos, por determinado idioma");
            System.out.println("9. Salir");

            System.out.print("Por favor, elige una opción: ");
            // Leer la elección del usuario con control de errores
            //try {
                option = Integer.parseInt(teclado.nextLine());
                var baseURL = "https://gutendex.com/books/";

                // Procesar la elección del usuario
                switch (option) {
                    case 1:
                        System.out.println("Has elegido la Opción 1. Buscar libro por titulo en la API de Gutendex");
                        System.out.println("Escribe el titulo o un fragmento de este para buscarlo");
                        var busqueda = teclado.nextLine();
                        var busquedaURL = busqueda.replace(" ", "%20");
                        // realizar consumo de datos de la API
                        var json = consumoApi.obtenerDatos(baseURL + "?search=" + busquedaURL);
                        System.out.println(json);
                        //transformar respuesta en objeto
                        var datos = conversorDatos.obtenerDatos(json, DatosResponses.class);
                        //System.out.println(datos);

                        //Tomar las respuesta (datos)>>acceder al atributo resultados (lista de tipo DatosLibros)
                        Optional<DatosLibros> resultados_optional = datos.resultados().stream()
                                .filter(l -> l.titulo().toUpperCase().contains(busqueda.toUpperCase())).findFirst();

                        if (resultados_optional.isPresent()) {
                            System.out.println("Total de registros encontrados: " + datos.cantidad());
                            System.out.println(resultados_optional.get().imprimeDatosLibro(datos));

                        } else { System.out.println("Libro no encontrado"); }
                        Thread.sleep(3000);
                    break;
//-----------------------------------------------------------------------
                    case 2:
                        System.out.println("Has elegido la Opción 3. Buscar libro por ID en la API de Gutendex");
                        System.out.println("Escribe el numero de ID del libro a consultar (1 al 75101)");
                        var busqueda3 = teclado.nextLine();
                        var busquedaURL3 = busqueda3.replace(" ", "%20");
                        // Realizar consumo de datos de la API
                        var json3 = consumoApi.obtenerDatos(baseURL + "?ids=" + busquedaURL3);
                        //System.out.println(json3);
                        // Transformar respuesta en objeto
                        var datos3 = conversorDatos.obtenerDatos(json3, DatosResponses.class);
                        //System.out.println(datos3);

                        // Verificar que la lista de resultados no sea null
                        if (datos3.resultados() != null && !datos3.resultados().isEmpty()) {
                            var datos3_libro = conversorDatos.obtenerDatos(json3, DatosLibros.class);
                            System.out.println(datos3_libro.imprimeDatosLibro(datos3));
                        } else {
                            System.out.println("No se encontraron resultados para el ID proporcionado");
                        }
                        Thread.sleep(3000);
                        break;

                    case 3:
                        System.out.println("Top de los 32 libros más descargados");
                        var json2 = consumoApi.obtenerDatos(baseURL);
                        var datos2 = conversorDatos.obtenerDatos(json2, DatosResponses.class);
                        System.out.println(datos2);
                        // Numeración de líneas
                        AtomicInteger contador2 = new AtomicInteger(1);
                        System.out.println("Resultados de la búsqueda:");
                        datos2.resultados().stream()
                                .sorted(Comparator.comparing(DatosLibros::numeroDescargas)
                                        .reversed()) .limit(10) .forEach(libro ->
                                { try { System.out.println(
                                        contador2.getAndIncrement() + ". " +
                                        libro.imprimeDatosLibro(datos2));
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                } });
                        Thread.sleep(3000);
                        break;
                    case 4:


                    break;

                    case 5:
//                        System.out.println("Has elegido la Opción 5. Lista de libros registrados en la base de datos:");//
//                        System.out.println("Escribe el numero de ID del libro a consultar (1 al 75101)");
//                        var idLibro4 = teclado.nextInt();
//                        if (idLibro4 < 75102 ) {
//                            var json4 = consumoApi.obtenerDatos("https://gutendex.com/books/" + idLibro4 + "/");
//                            var datos4 = conversorDatos.obtenerDatos(json4, DatosURLs.class);
//                            System.out.println(datos4.web());
//                            System.out.println(datos4.stream());
//
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
//            } catch (Exception e) {
//                System.out.println("Inténtelo de nuevo.");
//                teclado.nextLine(); // Limpiar el buffer del escáner
//                Thread.sleep(2000);
//                            }
        } while (option != 9);
        // Cerrar el scanner
        teclado.close();
    }
}
