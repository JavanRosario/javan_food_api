package com.javanfood.javanfood.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSemSenhaRequest {

    @NotBlank
    private String nome;

    @Email
    private String email;

}
