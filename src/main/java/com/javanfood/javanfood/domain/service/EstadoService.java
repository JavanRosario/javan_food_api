package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.api.dto.request.EstadoRequest;
import com.javanfood.javanfood.api.mapper.estadoMapper.EstadoRequestMapper;
import com.javanfood.javanfood.domain.exception.EntidadeEmUsoException;
import com.javanfood.javanfood.domain.exception.EstadoNaoEncontradoException;
import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.domain.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private static final String MSG_ENTIDADE_EM_USO = "Estado de código: %d não pode ser removido, pois está em uso";
    private final EstadoRepository estadoRepository;
    private final EstadoRequestMapper estadoRequestMapper;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscarOuFalha(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

    @Transactional
    public Estado atualizar(Long id, EstadoRequest estadoRequest) {
        Estado estado = buscarOuFalha(id);
        estadoRequestMapper.updateEntityFromDto(estadoRequest, estado);
        return salvar(estado);
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void excluir(Long estadoId) {

        if (!estadoRepository.existsById(estadoId)) {
            throw new EstadoNaoEncontradoException(estadoId);
        }

        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, estadoId));
        }
    }
}
