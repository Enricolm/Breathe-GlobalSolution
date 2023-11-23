package com.example.breathe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.breathe.models.Diagnostico;
import com.example.breathe.models.Doenca;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
        List<Diagnostico> findByDoenca(Doenca doenca);

    void deleteByDoenca(Doenca doenca);
}
