package com.example.breathe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.breathe.models.Bairro;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    Page<Bairro> findByNomeContaining(String busca, Pageable pageable);

}
