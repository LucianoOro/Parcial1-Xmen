package com.example.inicial1.services;

import com.example.inicial1.dtos.StatsDTO;
import com.example.inicial1.entities.DNA;

import java.util.Map;

public interface DNAService extends BaseService<DNA, Long>{

    // Metodo que determina si el ADN pertenece a un mutante
    boolean esMutante(String[] dna);

    // Métodos auxiliares para validaciones
    boolean isValidNxN(String[] dna);

    boolean isValidDnaCharacters(String[] dna);
    // Obtener estadísticas de las verificaciones

    // Metodo para guardar la secuencia de ADN.
    void saveDnaSequence(String[] dna, boolean esMutante);

    // Metodo para obtener las estadísticas.
    Map<String, Object> obtenerEstadisticas();

}
