package com.javanfood.javanfood.api.controllers;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javanfood.javanfood.api.dto.CozinhaModel;
import com.javanfood.javanfood.api.mapper.cozinhaMapper.CozinhaMapper;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.service.CadastroCozinhaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Autowired
	private CozinhaMapper cozinhaMapper;


	@GetMapping
	public List<CozinhaModel> listar() {
		return cozinhaMapper.toDtoCollection(cozinhaRepository.findAll());
	}

	@GetMapping("/{cozinha_id}")
	public Cozinha listarId(@PathVariable Long cozinha_id) {
		return cadastroCozinhaService.buscarOuFalha(cozinha_id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}

	@PutMapping("/{cozinha_id}")
	public Cozinha atualizar(@PathVariable Long cozinha_id, @RequestBody Cozinha cozinha) {

		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalha(cozinha_id);

		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

		return cadastroCozinhaService.salvar(cozinhaAtual);

	}

	@DeleteMapping("/{cozinha_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable long cozinha_id) {
		cadastroCozinhaService.excluir(cozinha_id);
	}
}
