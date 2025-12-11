package com.javanfood.javanfood.api.repository;

import com.javanfood.javanfood.domain.model.Estado;

import java.util.List;

public interface EstadoRespository {
    List<Estado> listar();

    Estado findById(Long id);

    Estado adicionar(Estado estado);

    void delete(Long id);
}
