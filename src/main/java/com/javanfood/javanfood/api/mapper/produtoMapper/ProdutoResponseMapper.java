package com.javanfood.javanfood.api.mapper.produtoMapper;

import com.javanfood.javanfood.api.dto.response.ProdutoResponse;
import com.javanfood.javanfood.domain.model.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoResponseMapper {

    ProdutoResponse toDto(Produto produto);

    List<ProdutoResponse> toDtoCollection(List<Produto> produtos);
}
