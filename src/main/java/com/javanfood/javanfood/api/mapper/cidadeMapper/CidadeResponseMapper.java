package com.javanfood.javanfood.api.mapper.cidadeMapper;

import com.javanfood.javanfood.api.dto.response.CidadeResponse;
import com.javanfood.javanfood.domain.model.Cidade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CidadeResponseMapper {

    CidadeResponse toDto(Cidade cidade);

    List<CidadeResponse> toDtoCollection(List<Cidade> cidades);
}
