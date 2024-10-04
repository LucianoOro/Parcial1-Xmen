package com.example.inicial1.Exceptions;

// Extiende de RuntimeException para que sea una excepción no verificada
public class InvalidDnaCharacterException extends RuntimeException {

    // Constructor que acepta un mensaje de error
    public InvalidDnaCharacterException(String message) {
        super(message);
    }

    // Puedes agregar más constructores o métodos según lo que necesites
}
