package com.javanfood.javanfood.api.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.javanfood.javanfood.domain.model.view.RestauranteView;

import java.math.BigDecimal;

public record RestauranteResponse(@JsonView(RestauranteView.class) Long id,
                                  @JsonView(RestauranteView.class) String nome, BigDecimal taxaFrete,
                                  CozinhaResponse cozinha, Boolean ativo,
                                  Boolean aberto, EnderecoResponse endereco) {
}
