package com.example.breathe.models;

import org.springframework.hateoas.EntityModel;

import com.example.breathe.controllers.BairroController;
import com.example.breathe.controllers.DoencaController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String zona;

    @ManyToOne
    private Cidade cidade;

    public EntityModel<Bairro> toEntityModel() {
    return EntityModel.of(
        this,
        linkTo(methodOn(BairroController.class).show(id)).withSelfRel(),
        // linkTo(methodOn(BairroController.class).index(null, Pageable.unpaged())).withRel("all"),
        linkTo(methodOn(BairroController.class).show(id)).withRel("destroy")  
    ); 
    }


}
