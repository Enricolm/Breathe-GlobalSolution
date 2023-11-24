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
import com.example.breathe.models.Cidade;
import com.example.breathe.repository.CidadeRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cidade")
@RequestMapping("/api/cidades")
public class CidadeController {
    
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 10) Pageable pageable){
        Page<Cidade> cidade = cidadeRepository.findAll(pageable);

        return assembler.toModel(cidade.map(Cidade::toEntityModel));
    }

    @PostMapping
    public ResponseEntity<Cidade> create(@RequestBody @Valid Cidade cidade){
        cidadeRepository.save(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
    }

    @GetMapping("{id}")
    public EntityModel<Cidade> show(@PathVariable long id) {
        var cidade = cidadeRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Cidade nao encontrada"));
        return cidade.toEntityModel();
    }

        @PutMapping("{id}")
    public EntityModel<Cidade> update(@PathVariable Long id, @RequestBody @Valid Cidade cidade){

        cidadeRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao apagar, doenca não encontrada"));

        cidade.setId(id);
        cidadeRepository.save(cidade);

        return cidade.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cidade> destroy(@PathVariable Long id){

        var cidade = cidadeRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Erro ao apagar, doenca não encontrada"));
        
        cidadeRepository.delete(cidade);
        return ResponseEntity.noContent().build();
    }


}
