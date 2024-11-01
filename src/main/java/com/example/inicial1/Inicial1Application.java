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
		
	}
}

