package com.javanfood.javanfood.repository;

import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Cozinha;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();

    Cidade findById(Long id);

    Cidade adicionar(Cidade cidade);

    void delete(Cidade cidade);
}
