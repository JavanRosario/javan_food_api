package com.javanfood.javanfood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
	public RestauranteNaoEncontradoException(String message) {
		super(message);
	}

	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Não existe um cadastro de Restaurante com código: %d", restauranteId));
	}
}
