package com.example.inicial1.repositories;

import com.example.inicial1.entities.DNA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DNARepository extends BaseRepository<DNA, Long> {
    Optional<DNA> findBySequence(String sequence);
    long countByesMutante(boolean esMutante);

    // Cuenta el número total de ADN mutantes.
    @Query("SELECT COUNT(d) FROM DNA d WHERE d.esMutante = true")
    long countMutantDna();

    // Cuenta el número total de ADN humanos (no mutantes).
    @Query("SELECT COUNT(d) FROM DNA d WHERE d.esMutante = false")
    long countHumanDna();
}
