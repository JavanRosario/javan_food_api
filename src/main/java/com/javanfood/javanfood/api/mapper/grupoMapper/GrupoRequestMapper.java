package com.javanfood.javanfood.api.mapper.grupoMapper;

import com.javanfood.javanfood.api.dto.request.GrupoRequest;
import com.javanfood.javanfood.domain.model.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GrupoRequestMapper {

    Grupo toDomainObject(GrupoRequest grupoRequest);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(GrupoRequest grupoRequest, @MappingTarget Grupo grupo);
}
