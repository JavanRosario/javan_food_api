package com.javanfood.javanfood.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoRequest {

    @NotBlank
    private String nome;
}
