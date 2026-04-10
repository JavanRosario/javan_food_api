package com.javanfood.javanfood.api.mapper.usuarioMapper;

import com.javanfood.javanfood.api.dto.request.UsuarioRequest;
import com.javanfood.javanfood.api.dto.request.UsuarioSemSenhaRequest;
import com.javanfood.javanfood.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioRequestMapper {
    Usuario toDomainObject(UsuarioRequest usuarioRequest);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UsuarioSemSenhaRequest usuarioSemSenhaRequest, @MappingTarget Usuario usuario);


}
