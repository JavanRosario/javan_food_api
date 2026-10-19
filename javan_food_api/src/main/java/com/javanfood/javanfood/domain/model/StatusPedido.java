package com.javanfood.javanfood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
    CRIADO,
    CONFIRMADO(CRIADO),
    ENTREGUE(CONFIRMADO),
    CANCELADO(CRIADO, CONFIRMADO);

    private List<StatusPedido> statusAnteriores;

    StatusPedido(StatusPedido... status) {
        this.statusAnteriores = Arrays.asList(status);
    }

    public boolean naoPodeAlterar(StatusPedido novoStatus) {
        return !novoStatus.statusAnteriores.contains(this);
    }
}
