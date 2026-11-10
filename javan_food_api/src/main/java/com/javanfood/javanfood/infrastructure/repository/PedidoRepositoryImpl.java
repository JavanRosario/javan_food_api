package com.javanfood.javanfood.infrastructure.repository;

import com.javanfood.javanfood.api.v1.dto.filters.VendaDiariaFilter;
import com.javanfood.javanfood.api.v1.dto.response.VendaDiariaResponse;
import com.javanfood.javanfood.domain.model.Pedido;
import com.javanfood.javanfood.domain.model.StatusPedido;
import com.javanfood.javanfood.domain.repository.queries.PedidoRepositoryQueries;
import com.javanfood.javanfood.domain.service.RestauranteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryQueries {

    private final EntityManager manager;

    public PedidoRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<VendaDiariaResponse> consultarVendasDiarias(VendaDiariaFilter filtro) {

        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiariaResponse.class);
        var root = query.from(Pedido.class);
        var restaurauteIdPath = root.get("restaurante").get("id");
        var predicates = new ArrayList<Predicate>();

        var dataCriacao = builder.function("date", Date.class, root.get("dataCriacao"));

        var selection =
                builder.construct(
                        VendaDiariaResponse.class,
                        restaurauteIdPath,
                        dataCriacao,
                        builder.count(root.get("id")),
                        builder.sum(root.get("valorTotal")));

        if (filtro.restauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante").get("id"), filtro.restauranteId()));
        }

        if (filtro.dataInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.dataInicio()));
        }

        if (filtro.dataFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.dataFim()));
        }

        predicates.add(root.get("statusPedido").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection).where(predicates.toArray(new Predicate[0])).groupBy(restaurauteIdPath,dataCriacao);

        return manager.createQuery(query).getResultList();
    }
}
