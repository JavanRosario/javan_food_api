package com.javanfood.javanfood.infrastructure.repository.spec;

import com.javanfood.javanfood.api.v1.dto.filters.PedidoFilter;
import com.javanfood.javanfood.domain.model.Pedido;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;

public final class PedidoSpecification {
    private PedidoSpecification() {
    }

    public static Specification<Pedido> specification(PedidoFilter pedidoFilter) {
        return (root, query, criteriaBuilder) -> {

            Specification<Pedido> filtros = Specification.where(doCliente(pedidoFilter.clienteId()))
                    .and(doRestaurante(pedidoFilter.restauranteId()))
                    .and(doDataDeCriacao(pedidoFilter.dataCriacaoInicio()))
                    .and(doDataDeCriacaoFim(pedidoFilter.dataCriacaoFim()).and(consultas()));

            return filtros.toPredicate(root, query, criteriaBuilder);
        };
    }

    public static Specification<Pedido> consultas() {
        return (root, query, criteriaBuilder) -> {
            if (Pedido.class.equals(query.getResultType())) {
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("usuario");
                root.fetch("itemPedido").fetch("produto");
                root.fetch("endereco").fetch("cidade").fetch("estado");
                root.fetch("restaurante");
            }


            return null;
        };
    }


    public static Specification<Pedido> doCliente(Long clienteId) {
        return (root, query, criteriaBuilder) -> {
            if (clienteId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("usuario").get("id"), clienteId);
        };
    }

    public static Specification<Pedido> doRestaurante(Long restauranteId) {
        return (root, query, criteriaBuilder) -> {
            if (restauranteId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("restaurante").get("id"), restauranteId);
        };
    }

    public static Specification<Pedido> doDataDeCriacao(OffsetDateTime dataInicio) {
        return (root, query, criteriaBuilder) -> {
            if (dataInicio == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), dataInicio);
        };

    }

    public static Specification<Pedido> doDataDeCriacaoFim(OffsetDateTime dataFim) {
        return (root, query, criteriaBuilder) -> {
            if (dataFim == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), dataFim);
        };

    }
}

//        private PedidoSpecification (){}
//        return ((root, query, criteriaBuilder) -> {
//            var predicate = new ArrayList<Predicate>();
//
//            root.fetch("restaurante").fetch("cozinha");
//            root.fetch("usuario");
//            root.fetch("itemPedido").fetch("produto");
//
//            if (pedidoFilter.clienteId() != null) {
//                predicate.add(criteriaBuilder.equal(root.get("usuario").get("id"), pedidoFilter.clienteId()));
//            }
//
//            if (pedidoFilter.restauranteId() != null) {
//                predicate.add(criteriaBuilder.equal(root.get("restaurante").get("id"), pedidoFilter.restauranteId()));
//            }
//
//            if (pedidoFilter.dataCriacaoInicio() != null) {
//                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.dataCriacaoInicio()));
//            }
//
//            if (pedidoFilter.dataCriacaoFim() != null) {
//                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.dataCriacaoFim()));
//            }
//
//            return Specification.where(porCozinhaId(pedidoFilter));
//        });



