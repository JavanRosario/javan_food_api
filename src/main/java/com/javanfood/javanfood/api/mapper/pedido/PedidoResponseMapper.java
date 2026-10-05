package com.javanfood.javanfood.api.mapper.pedido;

import com.javanfood.javanfood.api.dto.response.PedidoResponse;
import com.javanfood.javanfood.api.dto.response.PedidoResumoResponse;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoResponseMapper {

    @Mapping(source = "usuario", target = "cliente")
    @Mapping(source = "itemPedido", target = "itens")
    @Mapping(source = "endereco", target = "enderecoEntrega")
    PedidoResponse toDto(Pedido pedido);

    @Mapping(source = "usuario", target = "cliente")
    @Mapping(source = "itemPedido", target = "itens")
    PedidoResumoResponse pedido(Pedido pedido);

    List<PedidoResumoResponse> toDtoCollection(List<Pedido> pedidos);

    default String map(Estado estado) {
        if (estado == null) return null;
        return estado.getNome();
    }

}
