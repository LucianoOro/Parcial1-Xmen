package com.example.inicial1.services;

import com.example.inicial1.entities.DNA;

public interface DNAService extends BaseService<DNA, Long>{

    // Metodo que determina si el ADN pertenece a un mutante
    boolean isMutant(String[] dna);

    // Métodos auxiliares para validaciones
    boolean isValidNxN(String[] dna);

    boolean isValidDnaCharacters(String[] dna);

}
