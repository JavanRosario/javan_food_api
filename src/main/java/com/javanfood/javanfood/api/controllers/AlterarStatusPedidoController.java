package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.domain.service.AlteracaoPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
@RequiredArgsConstructor
public class AlterarStatusPedidoController {
    private final AlteracaoPedidoService alteracaoPedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmarPedido(@PathVariable Long pedidoId) {
        alteracaoPedidoService.confirmar(pedidoId);
    }
}
