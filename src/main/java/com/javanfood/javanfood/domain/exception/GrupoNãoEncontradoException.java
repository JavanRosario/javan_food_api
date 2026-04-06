package com.javanfood.javanfood.domain.exception;

public class GrupoNãoEncontradoException extends EntidadeNaoEncontradaException {
    public GrupoNãoEncontradoException(String message) {
        super(message);
    }

    public GrupoNãoEncontradoException(Long grupoId) {
        this(String.format("Não existe um cadastro de Grupo com código: %d", grupoId));
    }
}
