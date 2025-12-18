package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {
    List<Restaurante> find(String nome, BigDecimal txFreteInicial, BigDecimal txFreteFinal);

    List<Restaurante> findComFreteGratis(String nome);
}
