package com.javanfood.javanfood.domain.exception;

public class PagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public PagamentoNaoEncontradoException(String message) {
        super(message);
    }

    public PagamentoNaoEncontradoException(Long pagamentoId) {
        this(String.format("Não existe um cadastro de FormaPagamento com código: %d", pagamentoId));
    }
}
