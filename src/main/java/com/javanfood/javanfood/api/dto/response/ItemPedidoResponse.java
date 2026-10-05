package com.javanfood.javanfood.api.dto.response;

import java.math.BigDecimal;

public record ItemPedidoResponse(Long id,
                                 Integer quantidade,
                                 BigDecimal precoUnitario,
                                 BigDecimal precoTotal,
                                 String observacao) {
}
