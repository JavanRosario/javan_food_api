package com.javanfood.javanfood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
	public EstadoNaoEncontradoException(String message) {
		super(message);
	}

	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe um cadastro de Estado com código: %d", estadoId));
	}
}
