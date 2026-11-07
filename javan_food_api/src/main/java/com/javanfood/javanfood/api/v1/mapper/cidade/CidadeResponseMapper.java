package com.javanfood.javanfood.api.v1.mapper.cidade;

import com.javanfood.javanfood.api.v1.dto.response.CidadeResponse;
import com.javanfood.javanfood.domain.model.Cidade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CidadeResponseMapper {

    CidadeResponse toDto(Cidade cidade);

    List<CidadeResponse> toDtoCollection(List<Cidade> cidades);
}
