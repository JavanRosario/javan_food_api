package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.response.PermissaoResponse;
import com.javanfood.javanfood.api.mapper.permissaoMapper.PermissaoResponseMapper;
import com.javanfood.javanfood.domain.model.Grupo;
import com.javanfood.javanfood.domain.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("grupos/{grupoId}/permissao")
@RestController
@RequiredArgsConstructor
public class GrupoPermissaoController {
    private final GrupoService grupoService;
    private final PermissaoResponseMapper grupoResponseMapper;


    @GetMapping
    public List<PermissaoResponse> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalha(grupoId);
        return grupoResponseMapper.toDtoCollection(grupo.getPermissoes());
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);
    }
}
