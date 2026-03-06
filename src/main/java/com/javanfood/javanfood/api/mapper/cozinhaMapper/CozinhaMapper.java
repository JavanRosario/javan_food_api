package com.javanfood.javanfood.api.mapper.cozinhaMapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.javanfood.javanfood.api.dto.CozinhaModel;
import com.javanfood.javanfood.domain.model.Cozinha;

@Mapper(componentModel = "spring")
public interface CozinhaMapper {

	CozinhaModel cozinhaDto(Cozinha cozinha);

	List<CozinhaModel> toDtoCollection(List<Cozinha> cozinha);

}
