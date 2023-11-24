package com.example.breathe.models;

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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

import com.example.breathe.controllers.BairroController;
import com.example.breathe.controllers.EnderecoController;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String pontoReferencia;

    @NotBlank
    private String dataInicio;

    @NotBlank
    private String dataFim;

    @ManyToOne
    private Logradouro logradouro;

    public EntityModel<Endereco> toEntityModel() {
    return EntityModel.of(
        this,
        linkTo(methodOn(EnderecoController.class).show(id)).withSelfRel(),
        // linkTo(methodOn(EnderecoController.class).index(null, Pageable.unpaged())).withRel("all"),
        linkTo(methodOn(EnderecoController.class).show(id)).withRel("destroy")  
    ); 
    }


}
