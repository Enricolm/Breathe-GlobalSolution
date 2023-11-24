package com.example.breathe.models;

import org.springframework.hateoas.EntityModel;

import com.example.breathe.controllers.LogradouroController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Logradouro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern( regexp = "^[0-9]{5}-[0-9]{3}$", message = "CEP inválido")
    private String cep;

    @NotBlank
    private String nome;

    @NotNull
    private int numero;

    @ManyToOne
    private Bairro bairro;

    public EntityModel<Logradouro> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(LogradouroController.class).show(id)).withSelfRel(),
            // linkTo(methodOn(LogradouroController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(LogradouroController.class).show(id)).withRel("destroy")  
    ); 
    }

}
