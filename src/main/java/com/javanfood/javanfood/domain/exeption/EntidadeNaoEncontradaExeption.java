package com.javanfood.javanfood.domain.exeption;

public class EntidadeNaoEncontradaExeption extends RuntimeException {
    public EntidadeNaoEncontradaExeption(String message) {
        super(message);
    }
}
