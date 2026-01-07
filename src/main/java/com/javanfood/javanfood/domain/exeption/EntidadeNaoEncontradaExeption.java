package com.javanfood.javanfood.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEncontradaExeption extends NegocioExeption {
    public EntidadeNaoEncontradaExeption(String message) {
        super(message);
    }
}
