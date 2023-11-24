package com.example.breathe.models;

import org.springframework.hateoas.EntityModel;

import com.example.breathe.controllers.DoencaController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Doenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 255)
    private String nome; 

    @NotBlank @Size(min = 3, max = 255)
    private String descricao;

    @NotBlank @Size(min = 3, max = 255)
    private String recomendacao; 
    




    public EntityModel<Doenca> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(DoencaController.class).show(id)).withSelfRel(),
            // linkTo(methodOn(DoencaController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(DoencaController.class).show(id)).withRel("destroy")  
        ); 
    }

}
