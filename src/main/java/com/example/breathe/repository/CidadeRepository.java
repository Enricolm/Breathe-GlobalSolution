package com.example.breathe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.breathe.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Page<Cidade> findByNomeContaining(String busca, Pageable pageable);
}
