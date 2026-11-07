package com.javanfood.javanfood.api.v1.controller;

import com.javanfood.javanfood.api.v1.dto.filters.PedidoFilter;
import com.javanfood.javanfood.api.v1.dto.request.PedidoRequest;
import com.javanfood.javanfood.api.v1.dto.response.PedidoResponse;
import com.javanfood.javanfood.api.v1.dto.response.PedidoResumoResponse;
import com.javanfood.javanfood.api.v1.mapper.pedido.PedidoRequestMapper;
import com.javanfood.javanfood.api.v1.mapper.pedido.PedidoResponseMapper;
import com.javanfood.javanfood.domain.model.Pedido;
import com.javanfood.javanfood.domain.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoResponseMapper pedidoResponseMapper;
    private final PedidoService pedidoService;
    private final PedidoRequestMapper pedidoRequestMapper;

    @GetMapping
    public Page<PedidoResumoResponse> listar(@ParameterObject PedidoFilter pedidoFilter, @ParameterObject Pageable pageable) {
        return pedidoService.listar(pedidoFilter, pageable).map(pedidoResponseMapper::pedido);
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
