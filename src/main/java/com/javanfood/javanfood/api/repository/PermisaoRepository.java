package com.javanfood.javanfood.api.repository;

import com.javanfood.javanfood.domain.model.Permisao;

import java.util.List;

public interface PermisaoRepository {
    List<Permisao> listar();

    Permisao findById(Long id);

    Permisao adicionar(Permisao permisao);

    void delete(Permisao permisao);
}
