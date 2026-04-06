package com.javanfood.javanfood.domain.exception;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException {
	public CozinhaNaoEncontradoException(String message) {
		super(message);
	}

	public CozinhaNaoEncontradoException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de Cozinha com código: %d", cozinhaId));
	}
}
