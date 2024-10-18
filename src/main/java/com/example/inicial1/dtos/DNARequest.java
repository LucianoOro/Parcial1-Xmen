package com.example.inicial1.dtos;

import java.util.Arrays;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DNARequest {

    private String[] dna;

    @Override
    public String toString() {
        return Arrays.toString(dna);
    }
}