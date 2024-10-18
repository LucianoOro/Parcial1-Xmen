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
            String[] dna = dnaRequest.getDna();
            boolean esMutante = dnaService.esMutante(dna);

            // Guardar ADN en la base de datos
            dnaService.saveDnaSequence(dna, esMutante);

            return esMutante ?
                    new ResponseEntity<>("Es un mutante", HttpStatus.OK) :
                    new ResponseEntity<>("No es mutante", HttpStatus.FORBIDDEN);
        } catch (InvalidDnaCharacterException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
