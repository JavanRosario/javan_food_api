package com.javanfood.javanfood.api.dto.response;

public record EnderecoResponse(
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        CidadeResumoResponse cidade) {
}
