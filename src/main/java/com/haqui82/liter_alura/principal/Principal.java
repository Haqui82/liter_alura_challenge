package com.haqui82.liter_alura.principal;

import com.haqui82.liter_alura.api_service.ConsumoAPI;
import com.haqui82.liter_alura.api_service.ConversorDatos;
import com.haqui82.liter_alura.model.ApiData;
import com.haqui82.liter_alura.model.Libros;
import com.haqui82.liter_alura.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConversorDatos conversorDatos = new ConversorDatos();

    //Repositorios.declaraciones------------------------------
    @Autowired
    public LibrosRepository repositorio_libros;
    @Autowired
    public ApiDataRepository repositorio_respuestas;
    @Autowired
    public AutorRepository repositorio_autores;
    @Autowired
    public LanguagesRepository repositorio_languages;
    @Autowired
    public FormatsRepository repositorio_formats;



    // Repositorios.constructores-----------------------------
    public Principal(LibrosRepository repository) {
        this.repositorio_libros = repository;
    }

    public Principal(ApiDataRepository repository) {
        this.repositorio_respuestas = repository;
    }

    //------------------------------------------------------

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
                        System.out.println("Has elegido la Opción 1. Buscar libro por título en la API de Gutendex");
                        System.out.println("Escribe las palabras claves a buscar:");
                        var busqueda = teclado.nextLine();
                        var busquedaURL = busqueda.replace(" ", "%20");
                        // realizar consumo de datos de la API
                        var json = consumoApi.obtenerDatos(baseURL + "?search=" + busquedaURL);
                        System.out.println("Respuesta de la API" + json);
                        // transformar respuesta en objeto
                        var datos = Optional.ofNullable(conversorDatos.obtenerDatos(json, ApiData.class))
                                .orElseThrow(() -> new RuntimeException("Error al obtener datos"));
                        System.out.println("Respueta DatosResponse" + datos);

                        // Verificar que la lista de resultados no sea null y no esté vacía
                        if (datos.resultados() != null && !datos.resultados().isEmpty()) {
                            Optional<Libros> resultadosOptional = datos.resultados().stream()
                                    .filter(l -> l.titulo().toUpperCase().contains(busqueda.toUpperCase()))
                                    .findFirst();
                            System.out.println("Respuesta filtrada (variable optional): " + resultadosOptional);

                            resultadosOptional.ifPresentOrElse(libro -> {
                                System.out.println("Total de registros encontrados: " + datos.cantidad());
                                try {
                                    // Convertir libro a JSON
                                    var libroJson = Optional.ofNullable(conversorDatos.convertirAJson(libro))
                                            .orElseThrow(() -> new RuntimeException("Error al convertir libro a JSON"));
                                    System.out.println("Resultado devolver a Json:" + libroJson);

                                    // Convertir JSON a ApiData
                                    var libroResponses = Optional.ofNullable(conversorDatos.obtenerDatos(libroJson, ApiData.class))
                                            .orElseThrow(() -> new RuntimeException("Error al convertir JSON a ApiData"));
                                    System.out.println("Resultado volver Json a DatosResponse:" + libroResponses);

//                                    // Imprimir datos del libro
//                                    System.out.println("Seccion imprimir datos del libro:");
//                                    try {
//                                        System.out.println(libro.imprimeDatosLibro(libroResponses));
//                                    } catch (InterruptedException e) {
//                                        throw new RuntimeException(e);
//                                    }
                                    //FIN DE LA SECCION IMPRIMIR DATOS DEL LIBRO
                                    System.out.println("Seccion guardar en la base de datos:");
                                    // Guardar en la base de datos
                                    try {

                                    } catch (ObjectOptimisticLockingFailureException e) {
                                        System.out.println("Error al guardar en la base de datos: " + e.getMessage());
                                    }
//                                     NO TOCAR POR SI ACASO
//                                    System.out.println("Seccion guardar en la base de datos:");
//                                    // Guardar en la base de datos
//                                    try {
//                                        var managedLibro = repositorio.findById(libro.id_respuestas_api()).orElse(libro);
//                                        repositorio.save(managedLibro);
//                                    } catch (ObjectOptimisticLockingFailureException e) {
//                                        System.out.println("Error al guardar en la base de datos: " + e.getMessage());
//                                    }
//                                  FIN DE NO TOCAR POR SI ACASO
                                } catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                }
                            }, () -> System.out.println("Libro no encontrado"));
                        } else {
                            System.out.println("No se encontraron resultados para las palabras claves proporcionadas");
                        }

                        Thread.sleep(3000);
                        break;
//-----------------------------------------------------------------------
                    case 2:
                        System.out.println("Has elegido la Opción 2. Buscar libro por ID en la API de Gutendex");
                        System.out.println("Escribe el numero de ID del libro a consultar (1 al 75101)");
                        var busqueda3 = teclado.nextLine();
                        var busquedaURL3 = busqueda3.replace(" ", "%20");
                        // Realizar consumo de datos de la API
                        var json3 = consumoApi.obtenerDatos(baseURL + "?ids=" + busquedaURL3);
                        //System.out.println(json3);
                        // Transformar respuesta en objeto
                        var datos3 = conversorDatos.obtenerDatos(json3, ApiData.class);
                        //System.out.println(datos3);

                        // Verificar que la lista de resultados no sea null
                        if (datos3.resultados() != null && !datos3.resultados().isEmpty()) {
                            var datos3_libro = conversorDatos.obtenerDatos(json3, Libros.class);
                            System.out.println(datos3_libro.imprimeDatosLibro(datos3));
                        } else {
                            System.out.println("No se encontraron resultados para el ID proporcionado");
                        }
                        Thread.sleep(3000);
                        break;

                    case 3:
                        System.out.println("Top de los 32 libros más descargados");
                        var json2 = consumoApi.obtenerDatos(baseURL);
                        var datos2 = conversorDatos.obtenerDatos(json2, ApiData.class);
                        System.out.println(datos2);
                        // Numeración de líneas
                        AtomicInteger contador2 = new AtomicInteger(1);
                        System.out.println("Resultados de la búsqueda:");
                        datos2.resultados().stream()
                                .sorted(Comparator.comparing(Libros::numeroDescargas)
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
                        System.out.println("Has elegido la Opción 4. Buscar libro por ID en la API de Gutendex");
                        System.out.println("Escribe el numero de ID del libro a consultar (1 al 75101)");
                        var busqueda4 = teclado.nextLine();
                        var busquedaURL4 = busqueda4.replace(" ", "%20");
                        // Realizar consumo de datos de la API
                        var data_json = consumoApi.obtenerDatos(baseURL + "?ids=" + busquedaURL4);
                        System.out.println("Respuesta de la api: " + data_json);
                        // Transformar respuesta en objeto
                        var data_clase_response = conversorDatos.obtenerDatos(data_json, ApiData.class);
                        System.out.println("Respuesta clase response" + data_clase_response);

                        // Verificar que la lista de resultados no sea null
                        if (data_clase_response.resultados() != null && !data_clase_response.resultados().isEmpty()) {
                            var data_clase_libro = conversorDatos.obtenerDatos(data_json, Libros.class);
                        // IMPRIMIR RESPUESTA FORMATEADA
                            System.out.println("Respuesta clase libro: " + data_clase_libro.imprimeDatosLibro(data_clase_response));
                        // GUARDAR EN LA BASE DE DATOS
                        //repositorio_libros.save(data_clase_libro);

                            repositorio_respuestas.save(data_clase_response);
                        } else {
                            System.out.println("No se encontraron resultados para el ID proporcionado");
                        }
                        Thread.sleep(3000);
                        break;

                    case 5:
//                        System.out.println("Has elegido la Opción 5. Lista de libros registrados en la base de datos:");//
//                        System.out.println("Escribe el numero de ID del libro a consultar (1 al 75101)");
//                        var idLibro4 = teclado.nextInt();
//                        if (idLibro4 < 75102 ) {
//                            var data_json = consumoApi.obtenerDatos("https://gutendex.com/books/" + idLibro4 + "/");
//                            var data_clase_response = conversorDatos.obtenerDatos(data_json, URLs.class);
//                            System.out.println(data_clase_response.web());
//                            System.out.println(data_clase_response.stream());
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
