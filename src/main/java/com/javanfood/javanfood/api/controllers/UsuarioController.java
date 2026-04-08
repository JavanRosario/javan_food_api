package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.response.UsuarioResponse;
import com.javanfood.javanfood.api.mapper.usuarioMapper.UsuarioRequestMapper;
import com.javanfood.javanfood.api.mapper.usuarioMapper.UsuarioResponseMapper;
import com.javanfood.javanfood.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRequestMapper usuarioRequestMapper;
    private final UsuarioResponseMapper usuarioResponseMapper;


    @GetMapping
    List<UsuarioResponse> listar() {
        return usuarioResponseMapper.toDtoCollection(usuarioRepository.findAll());
    }
}
