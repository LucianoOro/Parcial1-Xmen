package com.example.inicial1.services;

import com.example.inicial1.Exceptions.InvalidDnaCharacterException;
import com.example.inicial1.Exceptions.InvalidDnaSizeException;
import com.example.inicial1.dtos.StatsDTO;
import com.example.inicial1.entities.DNA;
import com.example.inicial1.repositories.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DNAServiceImpl extends BaseServiceImpl<DNA, Long> implements DNAService {

    private final DNARepository dnaRepository;

    @Autowired
    public DNAServiceImpl(DNARepository dnaRepository) {
        super(dnaRepository);
        this.dnaRepository = dnaRepository;
    }

    @Override
    public boolean esMutante(String[] dna) {
        // Validación completa del ADN
        validateDnaMatrix(dna);

        int n = dna.length;

        // Verifica horizontalmente
        for (String row : dna) {
            if (countSequences(row) > 0) { // Si se encuentra al menos una secuencia
                return true; // Retorna true si se encuentra una secuencia
            }
        }

        // Verifica verticalmente
        for (int col = 0; col < n; col++) {
            StringBuilder column = new StringBuilder();
            for (int row = 0; row < n; row++) {
                column.append(dna[row].charAt(col));
            }
            if (countSequences(column.toString()) > 0) { // Si se encuentra al menos una secuencia
                return true; // Retorna true si se encuentra una secuencia
            }
        }

        // Verifica diagonal de izquierda a derecha
        for (int col = 0; col < n - 3; col++) {
            for (int row = 0; row < n - 3; row++) {
                StringBuilder diagonal = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    diagonal.append(dna[row + i].charAt(col + i));
                }
                if (countSequences(diagonal.toString()) > 0) { // Si se encuentra al menos una secuencia
                    return true; // Retorna true si se encuentra una secuencia
                }
            }
        }

        // Verifica diagonal de derecha a izquierda
        for (int col = 3; col < n; col++) {
            for (int row = 0; row < n - 3; row++) {
                StringBuilder diagonal = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    diagonal.append(dna[row + i].charAt(col - i));
                }
                if (countSequences(diagonal.toString()) > 0) { // Si se encuentra al menos una secuencia
                    return true; // Retorna true si se encuentra una secuencia
                }
            }
        }

        return false; // Retorna false si no hay secuencias encontradas
    }




    @Override
    public void saveDnaSequence(String[] dna, boolean esMutante) {
        DNA dnaEntity = new DNA();
        dnaEntity.setSequence(dna); // Ahora este metodo acepta el array sin problemas.
        dnaEntity.setEsMutante(esMutante);
        dnaRepository.save(dnaEntity); // Guarda la entidad en la base de datos.
    }

    private int countSequences(String sequence) {
        int count = 0;
        char lastChar = '\0';
        int charCount = 0;

        for (char c : sequence.toCharArray()) {
            if (c == lastChar) {
                charCount++;
                if (charCount == 4) {
                    count++;
                }
            } else {
                lastChar = c;
                charCount = 1;
            }
        }
        return count;
    }

    private void validateDnaMatrix(String[] dna) {
        if (!isValidNxN(dna)) {
            throw new InvalidDnaSizeException("La matriz de ADN no es de tamaño NxN.");
        }
        if (!isValidDnaCharacters(dna)) {
            throw new InvalidDnaCharacterException("El ADN contiene caracteres no válidos");
        }
    }

    @Override
    public boolean isValidNxN(String[] dna) {
        int n = dna.length;
        for (String row : dna) {
            if (row.length() != n) return false;
        }
        return true;
    }

    @Override
    public boolean isValidDnaCharacters(String[] dna) {
        String validChars = "ATCG";
        for (String row : dna) {
            for (char c : row.toCharArray()) {
                if (validChars.indexOf(c) == -1) return false;
            }
        }
        return true;
    }

    @Override
    public Map<String, Object> obtenerEstadisticas() {
        long mutantes = dnaRepository.countByesMutante(true);
        long humanos = dnaRepository.countByesMutante(false);


        double ratio;
        if (humanos == 0) {
            ratio = (mutantes > 0) ? 1.0 : 0.0; // Si hay mutantes y no humanos, retorna 1.0
        } else {
            ratio = (double) mutantes / humanos; // Cálculo normal si hay humanos
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("count_mutant_dna", mutantes);
        stats.put("count_human_dna", humanos);
        stats.put("ratio", ratio);

        return stats;
    }

}
