package com.example.inicial1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Audited
public class DNA extends Base {

    @Column
    private String secuencia;
    @Column
    private boolean esMutante;

    @OneToOne(mappedBy = "dna", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Persona persona;
}
