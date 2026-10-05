package com.javanfood.javanfood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PedidoRequest {

    @Valid
    @NotNull
    private RestauranteIdRequest restaurante;

    @Valid
    @NotNull
    private FormaPagamentoRequest formaPagamento;

    @Valid
    private EnderecoRequest enderecoEntrega;


    private LocalDateTime dataCriacao;


    @Valid
    @NotNull
    @Size(min = 1)
    private List<ItemPedidoRequest> itens = new ArrayList<>();


}
