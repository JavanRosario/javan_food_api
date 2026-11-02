package com.javanfood.javanfood.domain.infrastructure.specifications;

import com.javanfood.javanfood.domain.model.Pedido;
import com.javanfood.javanfood.domain.repository.projection.PedidoFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class PedidoSpecification {
    public static Specification<Pedido> pedidoSpecification(PedidoFilter pedidoFilter) {
        return ((root, query, criteriaBuilder) -> {
            var predicate = new ArrayList<Predicate>();

            root.fetch("restaurante").fetch("cozinha");
            root.fetch("usuario");
            root.fetch("itemPedido").fetch("produto");

            if (pedidoFilter.clienteId() != null) {
                predicate.add(criteriaBuilder.equal(root.get("usuario").get("id"), pedidoFilter.clienteId()));
            }

            if (pedidoFilter.restauranteId() != null) {
                predicate.add(criteriaBuilder.equal(root.get("restaurante").get("id"), pedidoFilter.restauranteId()));
            }

            if (pedidoFilter.dataCriacaoInicio() != null) {
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.dataCriacaoInicio()));
            }

            if (pedidoFilter.dataCriacaoFim() != null) {
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.dataCriacaoFim()));
            }

            return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
        });
    }
}
