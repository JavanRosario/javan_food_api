package com.javanfood.javanfood.api.repository;

import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listar();

    Restaurante findById(Long id);

    Restaurante adicionar(Restaurante Restaurante);

    void delete(Restaurante cozinha);
}
