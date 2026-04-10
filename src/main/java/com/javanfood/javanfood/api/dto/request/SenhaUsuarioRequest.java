package com.javanfood.javanfood.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SenhaUsuarioRequest {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;
}
