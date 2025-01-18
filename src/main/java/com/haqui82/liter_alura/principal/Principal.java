package com.haqui82.liter_alura.principal;

import com.haqui82.liter_alura.api_service.ConsumoAPI;
import com.haqui82.liter_alura.api_service.ConversorDatos;
import com.haqui82.liter_alura.model.DatosLibros;
import com.haqui82.liter_alura.model.DatosResponses;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConversorDatos conversorDatos = new ConversorDatos();

    public void muestraMenu() throws InterruptedException {

        // Variable para almacenar la elección del usuario
        int option = 0;
        // Menú de opciones
        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("Para consultar libros escoge una de las opciones:");
            System.out.println("1. Buscar libro por titulo y/o autor en la API de Gutendex");
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
                option = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer después de nextInt()
                var baseURL = "https://gutendex.com/books/";

                // Procesar la elección del usuario
                switch (option) {
                    case 1:
                        System.out.println("Has elegido la Opción 1. Buscar libro por autor y/o titulo en la API de Gutendex");
                        System.out.println("Escribe las palabras claves a buscar");
                        var busqueda = teclado.nextLine();
                        var busquedaURL = busqueda.replace(" ","%20");
                        var json = consumoApi.obtenerDatos(baseURL+"?search="+busquedaURL);
                        System.out.println(json);
                        var datos = conversorDatos.obtenerDatos(json, DatosResponses.class);
                        System.out.println(datos);
                        Thread.sleep(3000);
                        break;

                    case 2:
                        System.out.println("Has elegido la Opción 2. Buscar libro por ID en la API de Gutendex");
                        System.out.println("Escribe el numero de ID del libro a consultar (1 al 75101)");
                        var idLibro = teclado.nextInt();
                        if (idLibro < 75102 ) {
                            var json2 = consumoApi.obtenerDatos("https://gutendex.com/books/" + idLibro + "/");
                            var datos2 = conversorDatos.obtenerDatos(json2, DatosLibros.class);
                            System.out.println(datos2);
                            Thread.sleep(3000);
                        } else {
                            System.out.println("Ingreso no valido");
                            Thread.sleep(3000);
                        }
                        break;

                    case 3:
                        System.out.println("Top 10 de los libros más descargados");
                        var json3 = consumoApi.obtenerDatos(baseURL);
                        var datos3 = conversorDatos.obtenerDatos(json3, DatosResponses.class);
                        System.out.println(datos3);
                        Thread.sleep(3000);
                        datos3.resultados().stream()
                                .sorted(Comparator.comparing(DatosLibros::numeroDescargas).reversed())
                                .limit(10)
                                .forEach(System.out::println);
                        break;

                    case 4:
                        System.out.println("Has elegido la Opción 4. Lista de libros registrados en la base de datos:");
                        //invocacion(sufix2, sufix1);
                        break;

                    case 5:
                        System.out.println("Has elegido la Opción 5. Lista de autores registrados en la base de datos:");
                        //invocacion(sufix1, sufix3);
                        break;
                    case 6:
                        System.out.println("Has elegido la Opción 6. Lista de autores registrados en la base de datos; vivos en un determinado año");
                        //invocacion(sufix3, sufix1);
                        break;
                    case 7:
                        System.out.println("Has elegido la Opción 7. Listar libros registrados en la bases de datos, por determinado idioma");
                        //invocacion(sufix1, sufix4);
                        break;
                    case 8:
                        System.out.println("Has elegido la Opción 8. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida, por favor intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Inténtelo de nuevo.");
                teclado.nextLine(); // Limpiar el buffer del escáner
                Thread.sleep(2000);
                break;
            }
        } while (option != 8);
        // Cerrar el scanner
        teclado.close();
    }
}
