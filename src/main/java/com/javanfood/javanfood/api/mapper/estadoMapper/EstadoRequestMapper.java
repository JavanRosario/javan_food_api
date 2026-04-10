package com.javanfood.javanfood.api.mapper.estadoMapper;

import com.javanfood.javanfood.api.dto.request.EstadoRequest;
import com.javanfood.javanfood.domain.model.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EstadoRequestMapper {
    Estado toDomainObject(EstadoRequest estadoRequest);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(EstadoRequest estadoRequest, @MappingTarget Estado estado);
}
