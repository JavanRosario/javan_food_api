package com.javanfood.javanfood.api.v1.controller;

import com.javanfood.javanfood.api.v1.dto.response.UsuarioSemSenhaResponse;
import com.javanfood.javanfood.api.v1.mapper.usuario.UsuarioResponseMapper;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1restaurantes/{restauranteId}/usuarios")
@RequiredArgsConstructor
public class RestauranteUsuarioResponsavelController {
    private final RestauranteService restauranteService;
    private final UsuarioResponseMapper usuarioResponseMapper;

    @GetMapping
    public List<UsuarioSemSenhaResponse> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
        return usuarioResponseMapper.usuarioSemSenhaToDtoCollection(restaurante.getUsuariosResponsaveis());
    }
}
