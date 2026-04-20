package com.javanfood.javanfood.api.mapper.permissaoMapper;

import com.javanfood.javanfood.api.dto.response.PermissaoResponse;
import com.javanfood.javanfood.domain.model.Permissao;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissaoResponseMapper {

    PermissaoResponse toDto(Permissao permissao);

    List<PermissaoResponse> toDtoCollection(Collection<Permissao> permissoes);
}
