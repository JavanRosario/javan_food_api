package com.javanfood.javanfood.api.v1.controller;

import com.javanfood.javanfood.api.v1.dto.request.CozinhaRequest;
import com.javanfood.javanfood.api.v1.dto.response.CozinhaResponse;
import com.javanfood.javanfood.api.v1.mapper.cozinha.CozinhaRequestMapper;
import com.javanfood.javanfood.api.v1.mapper.cozinha.CozinhaResponseMapper;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.service.CozinhaService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cozinhas")
public class CozinhaController {

    private final CozinhaService cozinhaService;
    private final CozinhaResponseMapper cozinhaResponseMapper;
    private final CozinhaRequestMapper cozinhaRequestMapper;

    public CozinhaController(CozinhaService cozinhaService, CozinhaResponseMapper cozinhaResponseMapper,
                             CozinhaRequestMapper cozinhaRequestMapper) {
        this.cozinhaService = cozinhaService;
        this.cozinhaResponseMapper = cozinhaResponseMapper;
        this.cozinhaRequestMapper = cozinhaRequestMapper;
    }

    @GetMapping
    public Page<CozinhaResponse> listar(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return cozinhaService.listar(pageable).map(cozinhaResponseMapper::toDto);
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaResponse listarId(@PathVariable Long cozinhaId) {
        return cozinhaResponseMapper.toDto(cozinhaService.buscarOuFalha(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaResponse salvar(@RequestBody @Valid CozinhaRequest cozinhaRequest) {
        Cozinha cozinha = cozinhaRequestMapper.toDomainObject(cozinhaRequest);
        return cozinhaResponseMapper.toDto(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaResponse atualizar(@RequestBody @Valid CozinhaRequest cozinhaRequest, @PathVariable Long cozinhaId) {
        return cozinhaResponseMapper.toDto(cozinhaService.atualizar(cozinhaId, cozinhaRequest));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
