package com.javanfood.javanfood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
	public CozinhaNaoEncontradaException(String message) {
		super(message);
	}

	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de Cozinha com código: %d", cozinhaId));
	}
}
