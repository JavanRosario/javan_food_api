package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.response.FormaPagamentoResponse;
import com.javanfood.javanfood.api.mapper.formaPagamentoMapper.FormaPagamentoResponseMapper;
import com.javanfood.javanfood.domain.model.FormaPagamento;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
@RequiredArgsConstructor
public class RestauranteFormaPagamentoController {

    private final RestauranteService restauranteService;
    private final FormaPagamentoResponseMapper formaPagamentoResponseMapper;


    @GetMapping
    public List<FormaPagamentoResponse> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
        return formaPagamentoResponseMapper.toDtoCollection(restaurante.getFormasPagamento());
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoResponse buscarPorId(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
        FormaPagamento formaPagamento = restauranteService.getFormaPagamentoId(restauranteId, formaPagamentoId, restaurante);
        return formaPagamentoResponseMapper.toDto(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
    }
}
