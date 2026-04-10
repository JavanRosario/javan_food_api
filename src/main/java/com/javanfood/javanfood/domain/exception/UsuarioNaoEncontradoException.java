package com.javanfood.javanfood.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {
	public CidadeNaoEncontradoException(String message) {
		super(message);
	}

	public CidadeNaoEncontradoException(Long cidadeId) {
		this(String.format("Não existe um cadastro de Cidade com código: %d", cidadeId));
	}
}
