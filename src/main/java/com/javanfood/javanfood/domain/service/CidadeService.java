package com.javanfood.javanfood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.javanfood.javanfood.api.dto.request.CidadeRequest;
import com.javanfood.javanfood.api.mapper.cidade.CidadeRequestMapper;
import com.javanfood.javanfood.domain.exception.CidadeNaoEncontradaException;
import com.javanfood.javanfood.domain.exception.EntidadeEmUsoException;
import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.repository.CidadeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CidadeService {

	private static final String MSG_CIDADE_EM_USO = "Cidade de código: %d não pode ser removida, pois está em uso";
	private final CidadeRepository cidadeRepository;
	private final EstadoService estadoService;
	private final CidadeRequestMapper cidadeRequestMapper;

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade buscarOuFalha(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}

	@Transactional
	public Cidade atualizar(Long id, CidadeRequest request) {
		Cidade cidade = buscarOuFalha(id);
		cidadeRequestMapper.updateEntityFromDto(request, cidade);
		cidade.setEstado(estadoService.buscarOuFalha(request.getEstado().getId()));
		return salvar(cidade);
	}

	@Transactional
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoService.buscarOuFalha(estadoId);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	@Transactional
	public void excluir(Long cidadeId) {
		buscarOuFalha(cidadeId);
		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}
}
