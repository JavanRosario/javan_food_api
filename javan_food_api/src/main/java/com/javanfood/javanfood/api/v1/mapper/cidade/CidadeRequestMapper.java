package com.javanfood.javanfood.api.v1.mapper.cidade;

import com.javanfood.javanfood.api.v1.dto.request.CidadeRequest;
import com.javanfood.javanfood.domain.model.Cidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CidadeRequestMapper {

    Cidade toDomainObject(CidadeRequest cidadeRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateEntityFromDto(CidadeRequest cidadeRequest, @MappingTarget Cidade cidade);
}
