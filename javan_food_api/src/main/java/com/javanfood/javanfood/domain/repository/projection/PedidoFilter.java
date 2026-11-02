package com.javanfood.javanfood.domain.repository.projection;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

public record PedidoFilter(
        Long clienteId,
        Long restauranteId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        OffsetDateTime dataCriacaoInicio,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        OffsetDateTime dataCriacaoFim
) {
}