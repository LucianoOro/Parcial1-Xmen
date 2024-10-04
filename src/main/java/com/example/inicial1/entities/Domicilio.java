package com.example.inicial1.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Table(name="domicilio")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@Audited
public class Domicilio extends Base {
    @Column
    private String calle;
    @Column
    private int numero;

    @ManyToOne(optional = false)  //no puede ser nula, no es opcional, el domicilio siempre va a tener una localidad
    @JoinColumn(name = "fk_localidad")
    private Localidad localidad;


}
