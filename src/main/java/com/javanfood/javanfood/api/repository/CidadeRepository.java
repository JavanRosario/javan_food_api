package com.javanfood.javanfood.api.repository;

import com.javanfood.javanfood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();

    Cidade findById(Long id);

    Cidade adicionar(Cidade cidade);

    void delete(Cidade cidade);
}
