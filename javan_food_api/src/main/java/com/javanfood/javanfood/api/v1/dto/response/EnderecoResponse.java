package com.javanfood.javanfood.api.v1.dto.response;

public record EnderecoResponse(
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        CidadeResumoResponse cidade) {
}
