package com.example.inicial1.services;

import com.example.inicial1.Exceptions.InvalidDnaCharacterException;
import com.example.inicial1.Exceptions.InvalidDnaSizeException; // Importa la excepción
import com.example.inicial1.entities.DNA;
import com.example.inicial1.repositories.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DNAServiceImpl extends BaseServiceImpl<DNA, Long> implements DNAService {

    private final DNARepository dnaRepository;

    @Autowired
    public DNAServiceImpl(DNARepository dnaRepository) {
        super(dnaRepository);
        this.dnaRepository = dnaRepository;
    }

    @Override
    public boolean isMutant(String[] dna) {
        // Validación completa del ADN
        validateDnaMatrix(dna);

        int count = 0;
        int n = dna.length;

        // Verifica horizontalmente
        for (String row : dna) {
            count += countSequences(row);
        }

        // Verifica verticalmente
        for (int col = 0; col < n; col++) {
            StringBuilder column = new StringBuilder();
            for (int row = 0; row < n; row++) {
                column.append(dna[row].charAt(col));
            }
            count += countSequences(column.toString());
        }

        // Verifica diagonal de izquierda a derecha
        for (int col = 0; col < n - 3; col++) {
            for (int row = 0; row < n - 3; row++) {
                StringBuilder diagonal = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    diagonal.append(dna[row + i].charAt(col + i));
                }
                count += countSequences(diagonal.toString());
            }
        }

        // Verifica diagonal de derecha a izquierda
        for (int col = 3; col < n; col++) {
            for (int row = 0; row < n - 3; row++) {
                StringBuilder diagonal = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    diagonal.append(dna[row + i].charAt(col - i));
                }
                count += countSequences(diagonal.toString());
            }
        }

        return count >= 2; // Retorna true si hay 2 o más secuencias
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

    // Validación completa del ADN
    private void validateDnaMatrix(String[] dna) {
        if (!isValidNxN(dna)) {
            throw new InvalidDnaSizeException("La matriz de ADN no es de tamaño NxN."); // Lanza InvalidDnaSizeException
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

    // Verifica si hay 4 caracteres consecutivos iguales en la dirección indicada
    private boolean hasConsecutiveEqualChars(String[] dna, int row, int col, int rowStep, int colStep) {
        char firstChar = dna[row].charAt(col);
        for (int k = 1; k < 4; k++) {
            if (dna[row + k * rowStep].charAt(col + k * colStep) != firstChar) {
                return false;
            }
        }
        return true;
    }

    // Sobrecarga para verificar subcadenas horizontales en una fila
    private boolean hasConsecutiveEqualChars(String row, int start, int rowStep, int colStep) {
        char firstChar = row.charAt(start);
        for (int k = 1; k < 4; k++) {
            if (row.charAt(start + k * colStep) != firstChar) {
                return false;
            }
        }
        return true;
    }
}
