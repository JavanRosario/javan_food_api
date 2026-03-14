package com.javanfood.javanfood.api.controllers;


import com.javanfood.javanfood.api.dto.request.CozinhaRequest;
import com.javanfood.javanfood.api.dto.response.CozinhaResponse;
import com.javanfood.javanfood.api.mapper.cozinhaMapper.CozinhaRequestMapper;
import com.javanfood.javanfood.api.mapper.cozinhaMapper.CozinhaResponseMapper;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
@AllArgsConstructor
public class CozinhaController {
    private final CadastroCozinhaService cadastroCozinhaService;
    private final CozinhaResponseMapper cozinhaResponseMapper;
    private final CozinhaRepository cozinhaRepository;
    private final CozinhaRequestMapper cozinhaRequestMapper;

    @GetMapping
    public List<CozinhaResponse> listar() {
        return cozinhaResponseMapper.toDtoCollection(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinha_id}")
    public CozinhaResponse listarId(@PathVariable Long cozinha_id) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalha(cozinha_id);
        return cozinhaResponseMapper.cozinhaDto(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaResponse adicionar(@RequestBody @Valid CozinhaRequest cozinhaRequest) {

        Cozinha cozinha = cozinhaRequestMapper.toDomainObject(cozinhaRequest);
        cozinha = cadastroCozinhaService.salvar(cozinha);
        return cozinhaResponseMapper.cozinhaDto(cozinha);

    }

    @PutMapping("/{cozinha_id}")
    public CozinhaResponse atualizar(@PathVariable Long cozinha_id, @RequestBody CozinhaRequest cozinhaRequest) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalha(cozinha_id);

        cozinhaRequestMapper.updateEntityFromDto(cozinhaRequest, cozinhaAtual);

        return cozinhaResponseMapper.cozinhaDto(cadastroCozinhaService.salvar(cozinhaAtual));

    }

    @DeleteMapping("/{cozinha_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long cozinha_id) {
        cadastroCozinhaService.excluir(cozinha_id);
    }
}
