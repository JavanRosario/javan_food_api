package com.javanfood.javanfood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {


    public PedidoNaoEncontradoException(String codigo) {
        super(String.format("Não existe um cadastro de Pedido com código: %s", codigo));
    }
}
