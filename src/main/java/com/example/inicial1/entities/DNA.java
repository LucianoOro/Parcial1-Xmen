package com.example.inicial1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Table (name = "dna", uniqueConstraints = {@UniqueConstraint(columnNames = "sequence")})
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Audited

public class DNA extends Base {

    // Guardamos la secuencia de ADN como un único String.
    @Column(length = 1000) // Ajusta el tamaño según lo necesites.
    private String sequence;

    @Column (nullable = false)
    private boolean esMutante;

    // Setter que acepta un array de Strings y lo convierte en un único String.
    public void setSequence(String[] dna) {
        this.sequence = String.join(",", dna); // Concatenamos las filas con comas.
    }

    // Getter que devuelve la secuencia como un array de Strings.
    public String[] getSequence() {
        return sequence.split(","); // Separa el String en un array.
    }

    @OneToOne(mappedBy = "dna", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Persona persona;


}
