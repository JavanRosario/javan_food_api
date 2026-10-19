package com.javanfood.javanfood.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(String codigo, BigDecimal subTotal, BigDecimal taxaFrete, BigDecimal valorTotal,
                             String statusPedido, LocalDateTime dataCriacao,
                             LocalDateTime dataConfirmacao,
                             LocalDateTime dataEntrega,
                             LocalDateTime dataCancelamento,
                             RestauranteResumidoPedidoResponse restaurante,
                             UsuarioSemSenhaResponse cliente,
                             FormaPagamentoResponse formaPagamento,
                             EnderecoResponse enderecoEntrega,
                             List<ItemPedidoResponse> itens
) {
}
