package com.example.inicial1.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Audited
public class Persona extends Base {

    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Domicilio")
    private Domicilio domicilio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dna_id")
    private DNA dna;

}

