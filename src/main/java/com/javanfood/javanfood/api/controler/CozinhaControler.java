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

import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaControler {

		private final RestauranteControler restauranteControler;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;


	CozinhaControler(RestauranteControler restauranteControler) {
		this.restauranteControler = restauranteControler;
	}


	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{cozinha_id}")
	public void listarId(@PathVariable Long cozinha_id) {
		cadastroCozinhaService.buscarOuFalha(cozinha_id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
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
