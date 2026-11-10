package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.api.v1.dto.filters.VendaDiariaFilter;
import com.javanfood.javanfood.api.v1.dto.response.VendaDiariaResponse;
import com.javanfood.javanfood.domain.repository.queries.PedidoRepositoryQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EstatisticasService {
    private final PedidoRepositoryQueries pedidoRepositoryQueries;

    public List<VendaDiariaResponse> consultarVendas(VendaDiariaFilter vendaDiariaFilter) {
        return pedidoRepositoryQueries.consultarVendasDiarias(vendaDiariaFilter);
    }

}
