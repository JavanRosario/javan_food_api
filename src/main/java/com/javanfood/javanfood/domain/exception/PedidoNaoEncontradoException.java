package com.javanfood.javanfood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public PedidoNaoEncontradoException(String message) {
        super(message);
    }

    public PedidoNaoEncontradoException(Long pedidoId) {
        this(String.format("Não existe um cadastro de Pedido com código: %d", pedidoId));
    }
}
