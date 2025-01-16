package com.haqui82.liter_alura.principal;

import com.haqui82.liter_alura.api_service.ConsumoAPI;
import com.haqui82.liter_alura.api_service.ConversorDatos;
import com.haqui82.liter_alura.model.DatosLibro;

import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConversorDatos conversorDatos = new ConversorDatos();

    public void muestraMenu() throws InterruptedException {
        System.out.println("Escribe el numero de ID del libro a consultar");

        var idLibro = teclado.nextInt();
        var json = consumoApi.obtenerDatos("https://gutendex.com/books/"+idLibro+"/");
        var datos = conversorDatos.obtenerDatos(json, DatosLibro.class);
        System.out.println(json);
        System.out.println(datos);

        // Variable para almacenar la elección del usuario
        int option = 0;
        // Menú de opciones
        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("Para consultar libros escoge una de las opciones:");
            System.out.println("1. Buscar libro por titulo en la API de Gutendex");
            System.out.println("2. Listar libros registrados en la base de datos");
            System.out.println("3. Listar autores registrados en la base de datos");
            System.out.println("4. Listar autores registrados en la base de datos; vivos en un determinado año");
            System.out.println("5. Listar libros registrados en la bases de datos, por determinado idioma");
            System.out.println("6. Salir");

            System.out.print("Por favor, elige una opción: ");
            // Leer la elección del usuario con control de errores
            try {
                option = teclado.nextInt();
                // Procesar la elección del usuario
                switch (option) {
                    case 1:
                        System.out.println("Has elegido la Opción 1. Buscar libro por titulo en la API de Gutendex");
                        //invocacion();
                        break;
                    case 2:
                        System.out.println("Has elegido la Opción 2. Lista de libros registrados en la base de datos:");
                        //invocacion(sufix2, sufix1);
                        break;
                    case 3:
                        System.out.println("Has elegido la Opción 3. Lista de autores registrados en la base de datos:");
                        //invocacion(sufix1, sufix3);
                        break;
                    case 4:
                        System.out.println("Has elegido la Opción 4. Lista de autores registrados en la base de datos; vivos en un determinado año");
                        //invocacion(sufix3, sufix1);
                        break;
                    case 5:
                        System.out.println("Has elegido la Opción 5. Listar libros registrados en la bases de datos, por determinado idioma");
                        //invocacion(sufix1, sufix4);
                        break;
                    case 6:
                        System.out.println("Has elegido la Opción 6. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida, por favor intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Ingreso no válido. Inténtelo de nuevo.");
                teclado.next(); // Limpiar el buffer del escáner
                Thread.sleep(3000);
            }
        } while (option != 6);
        // Cerrar el scanner
        teclado.close();
    }
}
