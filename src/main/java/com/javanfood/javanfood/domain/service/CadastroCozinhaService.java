package com.javanfood.javanfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.javanfood.javanfood.domain.exeption.CozinhaNaoEncontradoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_ENTIDADE_EM_USO = "Cozinha de código: %d não pode ser removida, pois está em uso";


	@Autowired
	private CozinhaRepository cozinhaRepository;


	public Cozinha buscarOuFalha(Long cozinha_id) {
		return cozinhaRepository.findById(cozinha_id)
				.orElseThrow(() -> new CozinhaNaoEncontradoExeption(cozinha_id));
	}

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public void excluir(Long cozinhaId) {

		if (!cozinhaRepository.existsById(cozinhaId)) {
			throw new CozinhaNaoEncontradoExeption(cozinhaId);
		}
		try {
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(
					String.format(MSG_ENTIDADE_EM_USO, cozinhaId));
		}

	}


}
