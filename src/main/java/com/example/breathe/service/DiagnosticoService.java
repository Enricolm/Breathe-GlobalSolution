package com.example.breathe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.breathe.models.Doenca;
import com.example.breathe.repository.DiagnosticoRepository;

@Service
public class DiagnosticoService {

    private final DiagnosticoRepository diagnosticoRepository;

    @Autowired
    public DiagnosticoService(DiagnosticoRepository diagnosticoRepository) {
        this.diagnosticoRepository = diagnosticoRepository;
    }

    @Transactional
    public void deleteByDoenca(Doenca doenca) {
        diagnosticoRepository.deleteByDoenca(doenca);
    }
}