package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.repository.CidadeRepository;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

	private final CidadeRepository cidadeRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	CadastroCozinhaService(CidadeRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public void excluir(Long cozinhaId) {

		if (!cozinhaRepository.existsById(cozinhaId)) {
			throw new EntidadeNaoEncontradaExeption("Não existe cadastro de cozinha com código: " + cozinhaId);
		}

		try {
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption("Cozinha de código " + cozinhaId + " não pode ser removida. Está em uso");
		}

	}
}
