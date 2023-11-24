package com.example.breathe.models;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import com.example.breathe.controllers.EstadoController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String sigla;

    public EntityModel<Estado> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(EstadoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(EstadoController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(EstadoController.class).show(id)).withRel("destroy")  
        ); 
    }

}
