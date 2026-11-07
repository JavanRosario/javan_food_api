package com.javanfood.javanfood.api.v1.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoRequest {

    @NotNull
    Long produtoId;

    @NotNull
    @PositiveOrZero
    Integer quantidade;

    String observacao;
}
