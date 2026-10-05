package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.domain.service.AlteracaoPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{codigoPedido}")
@RequiredArgsConstructor
public class AlterarStatusPedidoController {
    private final AlteracaoPedidoService alteracaoPedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmarPedido(@PathVariable String codigoPedido) {
        alteracaoPedidoService.confirmar(codigoPedido);
    }

    @PutMapping("/entregar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregarPedido(@PathVariable String codigoPedido) {
        alteracaoPedidoService.entregar(codigoPedido);
    }

    @PutMapping("/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarPedido(@PathVariable String codigoPedido) {
        alteracaoPedidoService.calcelar(codigoPedido);
    }
}
