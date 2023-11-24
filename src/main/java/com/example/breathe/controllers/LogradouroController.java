package com.example.breathe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.breathe.exceptions.RestNotFoundException;
import com.example.breathe.models.Logradouro;
import com.example.breathe.repository.LogradouroRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Logradouros")
@RequestMapping("/api/logradouros")
public class LogradouroController {
    
    @Autowired
    LogradouroRepository logradouroRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    
    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 10) Pageable pageable){
        Page<Logradouro> logradouro = logradouroRepository.findAll(pageable);

        return assembler.toModel(logradouro.map(Logradouro::toEntityModel));
    }

    @PostMapping
    public ResponseEntity<Logradouro> create(@RequestBody @Valid Logradouro logradouro){
        logradouroRepository.save(logradouro);
        return ResponseEntity.status(HttpStatus.CREATED).body(logradouro);
    }

    @GetMapping("{id}")
    public EntityModel<Logradouro> show(@PathVariable long id) {
        var logradouro = logradouroRepository.findById(id).orElseThrow(() -> new RestNotFoundException("doenca nao encontrada"));
        return logradouro.toEntityModel();
    }

    @PutMapping("{id}")
    public EntityModel<Logradouro> update(@PathVariable Long id, @RequestBody @Valid Logradouro logradouro){

        logradouroRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao apagar, doenca não encontrada"));

        logradouro.setId(id);
        logradouroRepository.save(logradouro);

        return logradouro.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Logradouro> destroy(@PathVariable Long id){

        var logradouro = logradouroRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Erro ao apagar, doenca não encontrada"));
        
        logradouroRepository.delete(logradouro);
        return ResponseEntity.noContent().build();

    }

}
