package com.haqui82.liter_alura.principal;

import com.haqui82.liter_alura.api_service.ConsumoAPI;
import com.haqui82.liter_alura.api_service.ConversorDatos;
import com.haqui82.liter_alura.model.DatosLibro;

import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConversorDatos conversorDatos = new ConversorDatos();

    public void muestraMenu(){
        System.out.println("Escribe el numero de ID del libro a consultar");

        var idLibro = teclado.nextInt();
        var json = consumoApi.obtenerDatos("https://gutendex.com/books/"+idLibro+"/");
        var datos = conversorDatos.obtenerDatos(json, DatosLibro.class);
        System.out.println(json);
        System.out.println(datos);
    }
}
