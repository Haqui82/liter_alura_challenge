package com.haqui82.liter_alura;

import com.haqui82.liter_alura.api_service.ConsumoAPI;
import com.haqui82.liter_alura.api_service.ConversorDatos;
import com.haqui82.liter_alura.model.DatosLibro;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hola amantes de la literatura");
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obtenerDatos("https://gutendex.com/books/5/");
		System.out.println(json);
		/*ConversorDatos conversorDatos = new ConversorDatos();
		var datos = conversorDatos.obtenerDatos(json, DatosLibro.class);
		System.out.println(datos);*/

	}
}
