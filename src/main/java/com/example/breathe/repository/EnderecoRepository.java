package com.example.breathe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.breathe.models.Bairro;
import com.example.breathe.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Page<Endereco> findByLogradouroContaining(String busca, Pageable pageable);
}
