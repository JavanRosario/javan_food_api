package com.javanfood.javanfood.api.dto.response;

import java.math.BigDecimal;


public record RestauranteResponse(
        Long id,
        String nome,
        BigDecimal taxaFrete,
        CozinhaResponse cozinha
) {
}
