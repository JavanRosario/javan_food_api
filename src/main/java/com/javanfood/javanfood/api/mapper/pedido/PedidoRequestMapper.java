package com.javanfood.javanfood.api.mapper.pedido;

import com.javanfood.javanfood.api.dto.request.ItemPedidoRequest;
import com.javanfood.javanfood.api.dto.request.PedidoRequest;
import com.javanfood.javanfood.domain.model.ItemPedido;
import com.javanfood.javanfood.domain.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoRequestMapper {

    @Mapping(source = "itens", target = "itemPedido")
    @Mapping(source = "enderecoEntrega", target = "endereco")
    Pedido toObjectModel(PedidoRequest pedidoRequest);

    @Mapping(source = "produtoId", target = "produto.id")
    ItemPedido toItemPedido(ItemPedidoRequest itemPedidoRequest);

}
