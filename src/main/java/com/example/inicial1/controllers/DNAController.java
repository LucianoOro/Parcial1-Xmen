package com.example.inicial1.controllers;

import com.example.inicial1.Exceptions.InvalidDnaCharacterException;
import com.example.inicial1.dtos.DNARequest;
import com.example.inicial1.entities.DNA;
import com.example.inicial1.services.DNAService;
import com.example.inicial1.services.DNAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/mutant")
public class DNAController extends BaseControllerImpl<DNA, DNAServiceImpl> {

    @Autowired
    private DNAService dnaService;

    @GetMapping("/")
    public ResponseEntity<String> testEndpoint() {
        return new ResponseEntity<>("El endpoint está funcionando", HttpStatus.OK);
    }
    
    // Endpoint que recibe el ADN en formato JSON y determina si es mutante
    @PostMapping("/")
    public ResponseEntity<String> isMutant(@RequestBody DNARequest dnaRequest) {
        try {
            // Obtenemos la secuencia de ADN del objeto DNARequest
            String[] dna = dnaRequest.getDna();

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
