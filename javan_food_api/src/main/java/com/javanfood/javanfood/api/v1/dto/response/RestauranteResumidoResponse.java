package com.javanfood.javanfood.api.v1.dto.response;

import java.math.BigDecimal;


public record RestauranteResumidoResponse(
        Long id,
        String nome,
        BigDecimal taxaFrete,
        CozinhaResponse cozinha
) {
}
