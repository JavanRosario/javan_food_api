package com.javanfood.javanfood.api.dto.response;

public record CidadeResponse(
        Long id,
        String nome,
        EstadoResponse estado
) {
}
