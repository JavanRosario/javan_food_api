package com.javanfood.javanfood.api.controler;

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

import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.repository.EstadoRespository;
import com.javanfood.javanfood.domain.service.CadastroEstadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoControler {

	@Autowired
	private EstadoRespository estadoRespository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@GetMapping
	public List<Estado> listar() {
		return estadoRespository.findAll();
	}

	@GetMapping("/{estadoId}")
	public Estado listarid(@PathVariable Long estadoId) {
		return cadastroEstadoService.buscaOuFalha(estadoId);
	}

	@PostMapping
	public Estado adicionar(@RequestBody @Valid Estado estado) {
		return cadastroEstadoService.salvar(estado);

	}

	@PutMapping("/{enderecoId}")
	public Estado atualizar(@PathVariable Long enderecoId, @RequestBody @Valid Estado estado) {

		Estado estadoAtual = cadastroEstadoService.buscaOuFalha(enderecoId);
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return cadastroEstadoService.salvar(estadoAtual);
	}

	@DeleteMapping("/{enderecoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long enderecoId) {
		cadastroEstadoService.excluir(enderecoId);
	}

}
