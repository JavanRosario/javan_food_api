package com.javanfood.javanfood.api.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoRequest {

    @NotBlank
    @Column(nullable = false)
    private String nome;
}
