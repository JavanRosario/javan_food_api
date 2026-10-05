package com.javanfood.javanfood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
	public CidadeNaoEncontradaException(String message) {
		super(message);
	}

	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("Não existe um cadastro de Cidade com código: %d", cidadeId));
	}
}
