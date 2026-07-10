package com.javanfood.javanfood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Valid
    private EstadoIdRequest estado;
}
