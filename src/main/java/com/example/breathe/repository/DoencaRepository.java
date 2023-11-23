package com.example.breathe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.breathe.models.Doenca;

public interface DoencaRepository extends JpaRepository<Doenca, Long> {
    Page<Doenca> findByNomeContaining(String busca, Pageable pageable);
}
