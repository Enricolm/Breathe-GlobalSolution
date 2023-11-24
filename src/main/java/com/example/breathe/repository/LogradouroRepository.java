package com.example.breathe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.breathe.models.Logradouro;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {
    Page<Logradouro> findByNomeContaining(String busca, Pageable pageable);
    
}
