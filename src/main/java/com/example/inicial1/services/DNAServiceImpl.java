package com.example.inicial1.services;

import com.example.inicial1.Exceptions.InvalidDnaCharacterException;
import com.example.inicial1.entities.DNA;
import com.example.inicial1.repositories.BaseRepository;
import com.example.inicial1.repositories.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DNAServiceImpl extends BaseServiceImpl<DNA, Long> implements DNAService{
    @Autowired
    private DNARepository DNARepository;

    public DNAServiceImpl(BaseRepository<DNA, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public boolean isMutant(String[] dna) {
    // Primero validar la matriz
        if (!isValidNxN(dna)) {
        throw new InvalidDnaCharacterException("La matriz no es NxN");
    }

        if (!isValidDnaCharacters(dna)) {
        throw new InvalidDnaCharacterException("El ADN contiene caracteres no válidos");
    }

    // Verificar si el ADN pertenece a un mutante
    int n = dna.length;
    int sequenceCount = 0;

    // Verificación horizontal
        for (int i = 0; i < n; i++) {
        for (int j = 0; j <= n - 4; j++) {
            if (hasConsecutiveEqualChars(dna[i].substring(j, j + 4))) {
                sequenceCount++;
                if (sequenceCount > 1) return true;
            }
        }
    }

    // Verificación vertical
        for (int i = 0; i <= n - 4; i++) {
        for (int j = 0; j < n; j++) {
            if (hasConsecutiveEqualChars(new String(new char[]{dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j), dna[i + 3].charAt(j)}))) {
                sequenceCount++;
                if (sequenceCount > 1) return true;
            }
        }
    }

    // Verificación diagonal hacia abajo derecha
        for (int i = 0; i <= n - 4; i++) {
        for (int j = 0; j <= n - 4; j++) {
            if (hasConsecutiveEqualChars(new String(new char[]{dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3)}))) {
                sequenceCount++;
                if (sequenceCount > 1) return true;
            }
        }
    }

    // Verificación diagonal hacia arriba derecha
        for (int i = 3; i < n; i++) {
        for (int j = 0; j <= n - 4; j++) {
            if (hasConsecutiveEqualChars(new String(new char[]{dna[i].charAt(j), dna[i - 1].charAt(j + 1), dna[i - 2].charAt(j + 2), dna[i - 3].charAt(j + 3)}))) {
                sequenceCount++;
                if (sequenceCount > 1) return true;
            }
        }
    }

        return false;
}

// Validación de que la matriz sea NxN
@Override
public boolean isValidNxN(String[] dna) {
    int n = dna.length;
    for (String row : dna) {
        if (row.length() != n) {
            return false; // La matriz no es NxN
        }
    }
    return true;
}

// Validación de caracteres permitidos (A, T, C, G)
@Override
public boolean isValidDnaCharacters(String[] dna) {
    String validChars = "ATCG";
    for (String row : dna) {
        for (char c : row.toCharArray()) {
            if (validChars.indexOf(c) == -1) {
                return false; // Caracter no válido
            }
        }
    }
    return true;
}

// Metodo auxiliar para verificar cuatro caracteres consecutivos iguales
private boolean hasConsecutiveEqualChars(String sequence) {
    return sequence.charAt(0) == sequence.charAt(1) &&
            sequence.charAt(1) == sequence.charAt(2) &&
            sequence.charAt(2) == sequence.charAt(3);
}


}
