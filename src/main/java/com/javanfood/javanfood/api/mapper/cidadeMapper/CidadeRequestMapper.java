package com.javanfood.javanfood.api.mapper.cidadeMapper;

import com.javanfood.javanfood.api.dto.request.CidadeRequest;
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
