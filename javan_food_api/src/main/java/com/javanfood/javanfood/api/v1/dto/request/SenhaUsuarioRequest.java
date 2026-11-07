package com.javanfood.javanfood.api.v1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SenhaUsuarioRequest {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;
}
