package com.javanfood.javanfood.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaRequest {

    @NotBlank
    private String nome;

}
