package com.example.breathe.models;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import com.example.breathe.controllers.DiagnosticoController;
import com.example.breathe.controllers.DoencaController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doenca doenca;

    @ManyToOne
    private Usuario usuario;

    public EntityModel<Diagnostico> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(DiagnosticoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(DiagnosticoController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(DiagnosticoController.class).show(id)).withRel("destroy")
        );
    }

}
