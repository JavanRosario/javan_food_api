package com.javanfood.javanfood.api.mapper.produtoMapper;

import com.javanfood.javanfood.api.dto.request.ProdutoRequest;
import com.javanfood.javanfood.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoRequestMapper {
    Produto toDomainObject(ProdutoRequest produtoRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurante", ignore = true)
    void updateEntityFromDto(ProdutoRequest produtoRequest, @MappingTarget Produto produto);

}
