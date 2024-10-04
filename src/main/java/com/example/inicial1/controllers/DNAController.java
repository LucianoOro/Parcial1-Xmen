package com.example.inicial1.controllers;

import com.example.inicial1.Exceptions.InvalidDnaCharacterException;
import com.example.inicial1.entities.Localidad;
import com.example.inicial1.services.DNAService;
import com.example.inicial1.services.LocalidadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/dna")
public class DNAController extends BaseControllerImpl<Localidad, LocalidadServiceImpl>{

    @Autowired
    private DNAService dnaService;

    // Endpoint que recibe el ADN y determina si es mutante
    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody String[] dna) {
        try {
            // Si es mutante, respondemos con HTTP 200
            if (dnaService.isMutant(dna)) {
                return new ResponseEntity<>("Es un mutante", HttpStatus.OK);
            } else {
                // Si no es mutante, respondemos con HTTP 403
                return new ResponseEntity<>("No es mutante", HttpStatus.FORBIDDEN);
            }
        } catch (InvalidDnaCharacterException e) {
            // Si ocurre una excepción de ADN inválido, respondemos con HTTP 400
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}