package com.javanfood.javanfood.api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModel {
	private Long id;
	private String teste;
	private BigDecimal taxaFrete;
	private CozinhaModel cozinha;
}
