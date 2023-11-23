package com.example.breathe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.breathe.models.Diagnostico;
import com.example.breathe.models.Doenca;
import com.example.breathe.repository.DiagnosticoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Agencia")
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {
    @Autowired
    DiagnosticoRepository diagnosticoRepository;

    @PostMapping
    public ResponseEntity<Diagnostico> create(@RequestBody @Valid Diagnostico diagnostico){
        diagnosticoRepository.save(diagnostico);
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnostico);
    }
}
