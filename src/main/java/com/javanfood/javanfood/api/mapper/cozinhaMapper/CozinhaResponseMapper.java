package com.javanfood.javanfood.api.mapper.cozinhaMapper;

import java.util.List;

import com.javanfood.javanfood.api.dto.response.CozinhaResponse;
import org.mapstruct.Mapper;

import com.javanfood.javanfood.domain.model.Cozinha;

@Mapper(componentModel = "spring")
public interface CozinhaResponseMapper {

	CozinhaResponse toDto(Cozinha cozinha);

	List<CozinhaResponse> toDtoCollection(List<Cozinha> cozinha);

}
