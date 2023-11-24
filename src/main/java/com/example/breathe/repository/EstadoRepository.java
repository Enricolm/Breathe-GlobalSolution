package com.example.breathe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.breathe.models.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Page<Estado> findByNomeContaining(String busca, Pageable pageable);
    
}
