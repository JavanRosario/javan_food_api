package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.PedidoRequest;
import com.javanfood.javanfood.api.dto.response.PedidoResponse;
import com.javanfood.javanfood.api.dto.response.PedidoResumoResponse;
import com.javanfood.javanfood.api.mapper.pedido.PedidoRequestMapper;
import com.javanfood.javanfood.api.mapper.pedido.PedidoResponseMapper;
import com.javanfood.javanfood.domain.model.Pedido;
import com.javanfood.javanfood.domain.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoResponseMapper pedidoResponseMapper;
    private final PedidoService pedidoService;
    private final PedidoRequestMapper pedidoRequestMapper;


    @GetMapping

    public List<PedidoResumoResponse> listar() {
        return pedidoResponseMapper.toDtoCollection(pedidoService.listar());
    }

    @GetMapping("/{pedidoId}")
    public PedidoResponse buscarPorId(@PathVariable Long pedidoId) {
        return pedidoResponseMapper.toDto(pedidoService.buscarOuFalha(pedidoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse inserirPedido(@Valid @RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoRequestMapper.toObjectModel(pedidoRequest);
        return pedidoResponseMapper.toDto(pedidoService.adicionarPedido(pedido));
    }
}
