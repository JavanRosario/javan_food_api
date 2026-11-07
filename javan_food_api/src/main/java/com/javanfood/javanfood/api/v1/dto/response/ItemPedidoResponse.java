package com.javanfood.javanfood.api.v1.dto.response;

import java.math.BigDecimal;

public record ItemPedidoResponse(Long id,
                                 Integer quantidade,
                                 BigDecimal precoUnitario,
                                 BigDecimal precoTotal,
                                 String observacao) {
}
