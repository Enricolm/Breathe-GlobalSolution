package com.example.breathe.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.example.breathe.models.Doenca;
import com.example.breathe.models.Estado;
import com.example.breathe.repository.DiagnosticoRepository;
import com.example.breathe.repository.DoencaRepository;
import com.example.breathe.service.DiagnosticoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Doenca")
@RequestMapping("/api/doencas")
public class DoencaController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DoencaRepository doencaRepository;

    @Autowired
    DiagnosticoService diagnosticoService;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 10) Pageable pageable){
        Page<Doenca> doenca = doencaRepository.findAll(pageable);

        return assembler.toModel(doenca.map(Doenca::toEntityModel));
    }

    @PostMapping
    public ResponseEntity<Doenca> create(@RequestBody @Valid Doenca doenca){
        doencaRepository.save(doenca);
        return ResponseEntity.status(HttpStatus.CREATED).body(doenca);
    }
    
    @GetMapping("{id}")
    public EntityModel<Doenca> show(@PathVariable long id) {
        var doenca = doencaRepository.findById(id).orElseThrow(() -> new RestNotFoundException("doenca nao encontrada"));
        return doenca.toEntityModel();
    }

    @PutMapping("{id}")
    public EntityModel<Doenca> update(@PathVariable Long id, @RequestBody @Valid Doenca doenca){
        doencaRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Erro ao apagar, doenca não encontrada"));

        doenca.setId(id);
        doencaRepository.save(doenca);

        return doenca.toEntityModel();
    }

    
    @DeleteMapping("{id}")
    public ResponseEntity<Doenca> destroy(@PathVariable Long id){

        var doenca = doencaRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Erro ao apagar, doenca não encontrada"));
        
        diagnosticoService.deleteByDoenca(doenca);
        doencaRepository.delete(doenca);
        return ResponseEntity.noContent().build();

    }


}
