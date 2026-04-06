package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.FormaPagamentoRequest;
import com.javanfood.javanfood.api.dto.response.FormaPagamentoResponse;
import com.javanfood.javanfood.api.mapper.formaPagamentoMapper.FormaPagamentoRequestMapper;
import com.javanfood.javanfood.api.mapper.formaPagamentoMapper.FormaPagamentoResponseMapper;
import com.javanfood.javanfood.domain.model.FormaPagamento;
import com.javanfood.javanfood.domain.repository.FormaPagamentoRepository;
import com.javanfood.javanfood.domain.service.FormaPagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class FormaPagamentoController {
    private final FormaPagamentoRepository formaPagamentoRepository;
    private final FormaPagamentoResponseMapper formaPagamentoResponseMapper;
    private final FormaPagamentoService formaPagamentoService;
    private final FormaPagamentoRequestMapper formaPagamentoRequestMapper;


    @GetMapping
    public List<FormaPagamentoResponse> listar() {
        return formaPagamentoResponseMapper.toDtoCollection(formaPagamentoRepository.findAll());
    }

    @GetMapping("/{pagamentoId}")
    public FormaPagamentoResponse listarId(@PathVariable Long pagamentoId) {
        FormaPagamento formaPagamento = formaPagamentoService.buscaOuFalha(pagamentoId);
        return formaPagamentoResponseMapper.toDto(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoResponse adicionar(@RequestBody @Valid FormaPagamentoRequest formaPagamentoRequest) {
        FormaPagamento formaPagamento = formaPagamentoRequestMapper.toDomainObject(formaPagamentoRequest);
        return formaPagamentoResponseMapper.toDto(formaPagamentoService.salvar(formaPagamento));
    }

    @PutMapping("/{pagamentoId}")
    public FormaPagamentoResponse atualizar(@PathVariable Long pagamentoId, @RequestBody @Valid FormaPagamentoRequest formaPagamentoRequest) {
        FormaPagamento formaPagamento = formaPagamentoService.buscaOuFalha(pagamentoId);
        formaPagamentoRequestMapper.updateEntityFromDto(formaPagamentoRequest, formaPagamento);
        return formaPagamentoResponseMapper.toDto(formaPagamentoService.salvar(formaPagamento));
    }

    @DeleteMapping("/{pagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long pagamentoId) {
        formaPagamentoService.excluir(pagamentoId);
    }
}
