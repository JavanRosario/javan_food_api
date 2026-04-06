package com.javanfood.javanfood.api.controllers;


import com.javanfood.javanfood.api.dto.request.CozinhaRequest;
import com.javanfood.javanfood.api.dto.response.CozinhaResponse;
import com.javanfood.javanfood.api.mapper.cozinhaMapper.CozinhaRequestMapper;
import com.javanfood.javanfood.api.mapper.cozinhaMapper.CozinhaResponseMapper;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.service.CozinhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {
    private final CozinhaService cozinhaService;
    private final CozinhaResponseMapper cozinhaResponseMapper;
    private final CozinhaRepository cozinhaRepository;
    private final CozinhaRequestMapper cozinhaRequestMapper;

    @GetMapping
    public List<CozinhaResponse> listar() {
        return cozinhaResponseMapper.toDtoCollection(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaResponse listarId(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cozinhaService.buscarOuFalha(cozinhaId);
        return cozinhaResponseMapper.cozinhaDto(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaResponse adicionar(@RequestBody @Valid CozinhaRequest cozinhaRequest) {
        Cozinha cozinha = cozinhaRequestMapper.toDomainObject(cozinhaRequest);
        cozinha = cozinhaService.salvar(cozinha);
        return cozinhaResponseMapper.cozinhaDto(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaResponse atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaRequest cozinhaRequest) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalha(cozinhaId);

        cozinhaRequestMapper.updateEntityFromDto(cozinhaRequest, cozinhaAtual);

        return cozinhaResponseMapper.cozinhaDto(cozinhaService.salvar(cozinhaAtual));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
