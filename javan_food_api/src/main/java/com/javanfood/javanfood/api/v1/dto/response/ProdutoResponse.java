package com.javanfood.javanfood.api.v1.dto.response;

import java.math.BigDecimal;

public record ProdutoResponse(Long id, String nome, String descricao, BigDecimal preco, Boolean ativo) {
}
