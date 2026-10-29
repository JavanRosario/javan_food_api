package com.javanfood.javanfood.domain.repository.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"id", "nome", "taxaFrete", "cozinha"})
public interface RestauranteResumidoProjection {

    Long getId();

    String getNome();

    BigDecimal getTaxaFrete();

    CozinhaProjection getCozinha();

}
