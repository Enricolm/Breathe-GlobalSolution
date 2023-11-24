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
import com.example.breathe.models.Bairro;
import com.example.breathe.repository.BairroRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Bairro")
@RequestMapping("/api/bairros")
public class BairroController {
    @Autowired
    BairroRepository bairroRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 10) Pageable pageable){
        Page<Bairro> bairro = bairroRepository.findAll(pageable);

        return assembler.toModel(bairro.map(Bairro::toEntityModel));
    }

    @PostMapping
    public ResponseEntity<Bairro> create(@RequestBody @Valid Bairro bairro){
        bairroRepository.save(bairro);
        return ResponseEntity.status(HttpStatus.CREATED).body(bairro);
    }


    @GetMapping("{id}")
    public EntityModel<Bairro> show(@PathVariable long id) {
        var bairro = bairroRepository.findById(id).orElseThrow(() -> new RestNotFoundException("doenca nao encontrada"));
        return bairro.toEntityModel();
    }

    @PutMapping("{id}")
    public EntityModel<Bairro> update(@PathVariable Long id, @RequestBody @Valid Bairro bairro){

        bairroRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao apagar, doenca não encontrada"));

        bairro.setId(id);
        bairroRepository.save(bairro);

        return bairro.toEntityModel();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Bairro> destroy(@PathVariable Long id){

        var bairro = bairroRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Erro ao apagar, doenca não encontrada"));
        
        bairroRepository.delete(bairro);
        return ResponseEntity.noContent().build();
    }

}
