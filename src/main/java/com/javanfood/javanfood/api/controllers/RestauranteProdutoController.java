package com.javanfood.javanfood.api.controllers;

import com.javanfood.javanfood.api.dto.request.ProdutoRequest;
import com.javanfood.javanfood.api.dto.response.ProdutoResponse;
import com.javanfood.javanfood.api.mapper.produtoMapper.ProdutoRequestMapper;
import com.javanfood.javanfood.api.mapper.produtoMapper.ProdutoResponseMapper;
import com.javanfood.javanfood.domain.model.Produto;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.service.RestauranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
@RequiredArgsConstructor
public class RestauranteProdutoController {
    private final RestauranteService restauranteService;
    private final ProdutoResponseMapper produtoResponseMapper;
    private final ProdutoRequestMapper produtoRequestMapper;

    @GetMapping
    public List<ProdutoResponse> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
        return produtoResponseMapper.toDtoCollection(restaurante.getProdutos());
    }

    @GetMapping("/{produtoId}")
    public ProdutoResponse buscarPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
        Produto produto = restauranteService.getProdutoId(restauranteId, produtoId, restaurante);
        return produtoResponseMapper.toDto(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoRequest produtoRequest) {
        Produto produto = produtoRequestMapper.toDomainObject(produtoRequest);
        return produtoResponseMapper.toDto(restauranteService.adicionarProduto(restauranteId, produto));
    }

    @PutMapping("/{produtoId}")
    public ProdutoResponse atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProdutoRequest produtoRequest) {
        return produtoResponseMapper.toDto(restauranteService.atualizarProduto(restauranteId, produtoRequest, produtoId));
    }
}
