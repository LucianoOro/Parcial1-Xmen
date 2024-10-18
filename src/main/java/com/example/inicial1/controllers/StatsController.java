package com.example.inicial1.controllers;

import com.example.inicial1.dtos.StatsDTO;
import com.example.inicial1.services.DNAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/mutant")
public class StatsController {

    private final DNAService dnaService;

    @Autowired
    public StatsController(DNAService dnaService) {
        this.dnaService = dnaService;
    }
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        try {
            Map<String, Object> stats = dnaService.obtenerEstadisticas();
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

