package com.javanfood.javanfood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {
    public FormaPagamentoNaoEncontradaException(String message) {
        super(message);
    }

    public FormaPagamentoNaoEncontradaException(Long formaPagamentoId) {
        this(String.format("Não existe um cadastro de FormaPagamento com código: %d", formaPagamentoId));
    }
}
