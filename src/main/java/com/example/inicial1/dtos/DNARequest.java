package com.example.inicial1.dtos;

import java.util.Arrays;

public class DNARequest {
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        return Arrays.toString(dna);
    }
}