package com.javanfood.javanfood.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javanfood.javanfood.api.dto.request.ProdutoRequest;
import com.javanfood.javanfood.api.dto.response.ProdutoResponse;
import com.javanfood.javanfood.api.mapper.produto.ProdutoRequestMapper;
import com.javanfood.javanfood.api.mapper.produto.ProdutoResponseMapper;
import com.javanfood.javanfood.domain.model.Produto;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.service.ProdutoService;
import com.javanfood.javanfood.domain.service.RestauranteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
@RequiredArgsConstructor
public class RestauranteProdutoController {
	private final RestauranteService restauranteService;
	private final ProdutoResponseMapper produtoResponseMapper;
	private final ProdutoRequestMapper produtoRequestMapper;
	private final ProdutoService produtoService;

	@GetMapping
	public List<ProdutoResponse> listar(@PathVariable Long restauranteId,
			@RequestParam(required = false) boolean incluirInativos)   {

		Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
		if (incluirInativos) {
			return produtoResponseMapper.toDtoCollection(restaurante.getProdutos());
		}
		return produtoResponseMapper.toDtoCollection(produtoService.listarRestauranteAtivo(restaurante));

	}

	@GetMapping("/{produtoId}")
	public ProdutoResponse buscarPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Restaurante restaurante = restauranteService.buscarOuFalha(restauranteId);
		Produto produto = restauranteService.getProdutoId(restauranteId, produtoId, restaurante);
		return produtoResponseMapper.toDto(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoResponse adicionar(@PathVariable Long restauranteId,
			@RequestBody @Valid ProdutoRequest produtoRequest) {
		Produto produto = produtoRequestMapper.toDomainObject(produtoRequest);
		return produtoResponseMapper.toDto(restauranteService.adicionarProduto(restauranteId, produto));
	}

	@PutMapping("/{produtoId}")
	public ProdutoResponse atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoRequest produtoRequest) {
		return produtoResponseMapper
				.toDto(restauranteService.atualizarProduto(restauranteId, produtoRequest, produtoId));
	}
}
