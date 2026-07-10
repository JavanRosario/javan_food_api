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

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoService.listar();
//        List<PedidoResumoResponse> todosPedidos = pedidoResponseMapper.toDtoCollection(pedidos);
//
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(todosPedidos);
//
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//        if (StringUtils.isNotBlank(campos)) {
//            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//
//
//        pedidosWrapper.setFilters(filterProvider);
//
//        return pedidosWrapper;
//    }

    @GetMapping("/{codigoPedido}")
    public PedidoResponse buscarPorId(@PathVariable String codigoPedido) {
        return pedidoResponseMapper.toDto(pedidoService.buscarOuFalha(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse inserirPedido(@Valid @RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoRequestMapper.toObjectModel(pedidoRequest);
        return pedidoResponseMapper.toDto(pedidoService.adicionarPedido(pedido));
    }
}
