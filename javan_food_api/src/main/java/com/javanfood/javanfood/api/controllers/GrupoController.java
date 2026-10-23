package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.GrupoRequest;
import com.javanfood.javanfood.api.dto.response.GrupoResponse;
import com.javanfood.javanfood.api.mapper.grupo.GrupoRequestMapper;
import com.javanfood.javanfood.api.mapper.grupo.GrupoResponseMapper;
import com.javanfood.javanfood.domain.model.Grupo;
import com.javanfood.javanfood.domain.service.GrupoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grupos")
public class GrupoController {

    private final GrupoService grupoService;
    private final GrupoResponseMapper grupoResponseMapper;
    private final GrupoRequestMapper grupoRequestMapper;

    public GrupoController(GrupoService grupoService, GrupoResponseMapper grupoResponseMapper, GrupoRequestMapper grupoRequestMapper) {
        this.grupoService = grupoService;
        this.grupoResponseMapper = grupoResponseMapper;
        this.grupoRequestMapper = grupoRequestMapper;
    }

    @GetMapping
    public List<GrupoResponse> listar() {
        return grupoResponseMapper.toDtoCollection(grupoService.listar());
    }

    @GetMapping("/{grupoId}")
    public GrupoResponse listarId(@PathVariable Long grupoId) {
        return grupoResponseMapper.toDto(grupoService.buscarOuFalha(grupoId));
    }

    @PostMapping
    public GrupoResponse criar(@RequestBody GrupoRequest grupoRequest) {
        Grupo grupo = grupoRequestMapper.toDomainObject(grupoRequest);
        return grupoResponseMapper.toDto(grupoService.salvar(grupo));
    }

    @PutMapping("/{grupoId}")
    public GrupoResponse listar(@RequestBody GrupoRequest grupoRequest, @PathVariable Long grupoId) {
        return grupoResponseMapper.toDto(grupoService.atualizar(grupoId, grupoRequest));
    }

    @DeleteMapping("/{grupoId}")
    public void deleter(Long grupoId) {
        grupoService.excluir(grupoId);
    }


}
