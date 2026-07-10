package com.javanfood.javanfood.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//@JsonFilter("pedidoFilter")
public record PedidoResumoResponse(String codigo, BigDecimal subTotal, BigDecimal taxaFrete, BigDecimal valorTotal,
		String statusPedido, LocalDateTime dataCriacao, RestauranteResumidoPedidoResponse restaurante,
		UsuarioSemSenhaResponse cliente, List<ItemPedidoResponse> itens) {
}
