package com.example.inicial1;

import com.example.inicial1.Exceptions.InvalidDnaCharacterException;
import com.example.inicial1.Exceptions.InvalidDnaSizeException;
import com.example.inicial1.repositories.DNARepository;
import com.example.inicial1.repositories.LocalidadRepository;
import com.example.inicial1.repositories.PersonaRepository;
import com.example.inicial1.services.DNAServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Inicial1Application {
	private static final Logger logger = LoggerFactory.getLogger(Inicial1Application.class);

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private DNARepository dnaRepository;

	@Autowired
	private LocalidadRepository localidadRepository;

	@Autowired
	private DNAServiceImpl dnaService;

	public static void main(String[] args) {
		// Inicia la aplicación y obtiene el contexto de Spring Boot
		ApplicationContext context = SpringApplication.run(Inicial1Application.class, args);

		// Obtiene el bean del servicio DNAServiceImpl desde el contexto
		DNAServiceImpl dnaService = context.getBean(DNAServiceImpl.class);

		System.out.println("Análisis mutante en proceso");



		/* // Ejemplos de ADN para probar
		String[] dnaMutant = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"
		};

		String[] dnaHuman = {
				"ATGCGA",
				"CAGTGC",
				"TTATTT",
				"AGACGG",
				"GCGTCA",
				"TCACTG"
		};


		// Prueba del ADN mutante
		System.out.println("¿Es mutante? (debería ser true): " + dnaService.isMutant(dnaMutant));

		// Prueba del ADN humano
		System.out.println("¿Es mutante? (debería ser false): " + dnaService.isMutant(dnaHuman));

		// Ejemplo que no es de NxN (esto debería lanzar InvalidDnaSizeException)
		try {
			String[] dnaNotNxN = {
					"ATGCGA",
					"CAGT",  // Solo tiene 4 caracteres
					"TTATGT",
					"AGAAGG",
					"CCCCTA"
			};
			System.out.println("¿Es mutante? (esto debería lanzar una excepción): " + dnaService.isMutant(dnaNotNxN));
		} catch (InvalidDnaSizeException e) {
			System.out.println("Excepción capturada: " + e.getMessage());
		}

		// Ejemplo con caracteres inválidos (esto debería lanzar InvalidDnaCharacterException)
		try {
			String[] dnaWithInvalidCharacters = {
					"ATGCGA",
					"CAGTXC",  // Contiene 'X' que es inválido
					"TTATGT",
					"AGAAGG",
					"CCCCTA",
					"TCACTG"
			};
			System.out.println("¿Es mutante? (esto debería lanzar una excepción): " + dnaService.isMutant(dnaWithInvalidCharacters));
		} catch (InvalidDnaCharacterException e) {
			System.out.println("Excepción capturada: " + e.getMessage());
		}*/
	}
}

