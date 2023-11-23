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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.breathe.exceptions.RestNotFoundException;
import com.example.breathe.models.Doenca;
import com.example.breathe.models.Estado;
import com.example.breathe.repository.EstadoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Estado")
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 10) Pageable pageable){
        Page<Estado> estado = estadoRepository.findAll(pageable);

        return assembler.toModel(estado.map(Estado::toEntityModel));
    }

    @PostMapping
    public ResponseEntity<Estado> create(@RequestBody @Valid Estado estado) {
        // Adicione logs para depuração
        System.out.println("Estado recebido: " + estado);
    
        estadoRepository.save(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }


    @GetMapping("{id}")
    public EntityModel<Estado> show(@PathVariable long id) {
        var estado = estadoRepository.findById(id).orElseThrow(() -> new RestNotFoundException("doenca nao encontrada"));
        return estado.toEntityModel();
    }
    
}
