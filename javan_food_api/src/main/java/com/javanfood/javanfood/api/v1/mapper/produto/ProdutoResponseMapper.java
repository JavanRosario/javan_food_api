package com.javanfood.javanfood.api.v1.mapper.produto;

import com.javanfood.javanfood.api.v1.dto.response.ProdutoResponse;
import com.javanfood.javanfood.domain.model.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoResponseMapper {

    ProdutoResponse toDto(Produto produto);

    List<ProdutoResponse> toDtoCollection(List<Produto> produtos);
}
