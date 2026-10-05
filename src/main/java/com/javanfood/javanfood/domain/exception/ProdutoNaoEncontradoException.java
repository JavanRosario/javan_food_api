package com.javanfood.javanfood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }

    public ProdutoNaoEncontradoException(Long grupoId) {
        this(String.format("Não existe um cadastro de Produto com código: %d", grupoId));
    }
}
