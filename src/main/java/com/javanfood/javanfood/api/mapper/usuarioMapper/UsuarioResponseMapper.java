package com.javanfood.javanfood.api.mapper.usuarioMapper;

import com.javanfood.javanfood.api.dto.response.UsuarioResponse;
import com.javanfood.javanfood.domain.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioResponseMapper {
    UsuarioResponse toDto(Usuario usuario);

    List<UsuarioResponse> toDtoCollection(List<Usuario> usuarios);
}
