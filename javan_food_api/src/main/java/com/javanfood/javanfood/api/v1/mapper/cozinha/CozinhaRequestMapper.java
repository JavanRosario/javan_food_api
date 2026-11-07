package com.javanfood.javanfood.api.v1.mapper.cozinha;

import com.javanfood.javanfood.api.v1.dto.request.CozinhaRequest;
import com.javanfood.javanfood.domain.model.Cozinha;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CozinhaRequestMapper {

    Cozinha toDomainObject(CozinhaRequest cozinhaRequest);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CozinhaRequest cozinhaRequest, @MappingTarget Cozinha cozinha);
}
