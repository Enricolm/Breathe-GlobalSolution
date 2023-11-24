package com.example.breathe.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

import com.example.breathe.controllers.CidadeController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int ddd;

    @NotBlank
    private String nome;

    private int codIBGE;

    @ManyToOne
    private Estado estado;  

    public EntityModel<Cidade> toEntityModel() {
    return EntityModel.of(
        this,
        linkTo(methodOn(CidadeController.class).show(id)).withSelfRel(),
        // linkTo(methodOn(CidadeController.class).index(null, Pageable.unpaged())).withRel("all"),
        linkTo(methodOn(CidadeController.class).show(id)).withRel("destroy")  
    ); 
}

}
