package com.javanfood.javanfood.api.mapper.grupoMapper;

import com.javanfood.javanfood.api.dto.response.GrupoResponse;
import com.javanfood.javanfood.domain.model.Grupo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoResponseMapper {

    GrupoResponse toDto(Grupo grupo);

    List<GrupoResponse> toDtoCollection(List<Grupo> grupos);
}
