package com.haqui82.liter_alura;

import com.haqui82.liter_alura.principal.Principal;
import com.haqui82.liter_alura.repository.LibrosRepository;
import com.haqui82.liter_alura.repository.ApiDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.haqui82.liter_alura")
public class LiterAluraApplication implements CommandLineRunner {

	// Inyección de dependencias
	private final LibrosRepository repository_libros;
	private final ApiDataRepository repository_responses;

	@Autowired
	public LiterAluraApplication(LibrosRepository repository_libros, ApiDataRepository repository_responses) {
		this.repository_libros = repository_libros;
		this.repository_responses = repository_responses;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal_libros = new Principal(repository_libros);
		principal_libros.muestraMenu();
	}

	@Bean
	public Principal principal(ApiDataRepository repository_responses) {
		return new Principal(repository_responses);
	}
}
