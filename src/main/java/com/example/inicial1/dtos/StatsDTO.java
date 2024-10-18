package com.example.inicial1.dtos;

import lombok.Getter;

@Getter
public class StatsDTO {

    // Getters
    private long countMutantDna;
    private long countHumanDna;
    private double ratio;

    public StatsDTO(long countMutantDna, long countHumanDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = (countHumanDna == 0) ? 0 : (double) countMutantDna / countHumanDna;
    }

}
