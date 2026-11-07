package com.javanfood.javanfood.api.v1.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//@JsonFilter("pedidoFilter")
public record PedidoResumoResponse(String codigo, BigDecimal subTotal, BigDecimal taxaFrete, BigDecimal valorTotal,
		String statusPedido, LocalDateTime dataCriacao, RestauranteResumidoPedidoResponse restaurante,
		String nomeCliente, List<ItemPedidoResponse> itens) {
}
