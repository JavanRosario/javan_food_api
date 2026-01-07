package com.javanfood.javanfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.exeption.NegocioExeption;
import com.javanfood.javanfood.domain.exeption.RestauranteNaoEncontradoExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.PagamentoRepository;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String MSG_ENTIDADE_EM_USO = "Restaurante de código: %d não pode ser removida, pois está em uso";


	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	CadastroCozinhaService cozinhaService;

	@Autowired
	PagamentoRepository pagamentoRepository;

	public Restaurante buscarOuFalha(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoExeption(restauranteId));
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaService.buscarOuFalha(cozinhaId);
		try {
			restaurante.setCozinha(cozinha);
			return restauranteRepository.save(restaurante);
		} catch (EntidadeNaoEncontradaExeption e) {
			throw new NegocioExeption(String.format(MSG_ENTIDADE_EM_USO, restaurante));
		}

	}

	public void excluir(Long restauranteId) {

		if (!restauranteRepository.existsById(restauranteId)) {
			throw new RestauranteNaoEncontradoExeption(restauranteId);
		}

		try {
			restauranteRepository.deleteById(restauranteId);
			restauranteRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(String.format(MSG_ENTIDADE_EM_USO, restauranteId));
		}
	}
}

