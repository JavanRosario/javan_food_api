package com.javanfood.javanfood.api.v1.dto.response;

public record CidadeResponse(
        Long id,
        String nome,
        EstadoResponse estado
) {
}
