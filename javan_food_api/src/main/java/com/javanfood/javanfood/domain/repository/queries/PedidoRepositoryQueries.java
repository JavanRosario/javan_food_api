package com.javanfood.javanfood.domain.repository.queries;

import com.javanfood.javanfood.api.v1.dto.filters.VendaDiariaFilter;
import com.javanfood.javanfood.api.v1.dto.response.VendaDiariaResponse;

import java.util.List;

public interface PedidoRepositoryQueries {

    List<VendaDiariaResponse> consultarVendasDiarias(VendaDiariaFilter vendaDiariaFilter);
}
