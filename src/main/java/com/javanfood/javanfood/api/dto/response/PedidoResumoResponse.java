package com.javanfood.javanfood.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResumoResponse(Long id, BigDecimal subTotal, BigDecimal taxaFrete, BigDecimal valorTotal,
                                   String statusPedido, LocalDateTime dataCriacao,
                                   RestauranteResumidoResponse restaurante,
                                   UsuarioSemSenhaResponse cliente,
                                   List<ItemPedidoResponse> itens
) {
}
