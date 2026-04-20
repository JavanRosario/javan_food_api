package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.api.dto.request.CozinhaRequest;
import com.javanfood.javanfood.api.mapper.cozinhaMapper.CozinhaRequestMapper;
import com.javanfood.javanfood.domain.exception.CozinhaNaoEncontradaException;
import com.javanfood.javanfood.domain.exception.EntidadeEmUsoException;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CozinhaService {

    private static final String MSG_ENTIDADE_EM_USO = "Cozinha de código: %d não pode ser removida, pois está em uso";
    private final CozinhaRepository cozinhaRepository;
    private final CozinhaRequestMapper cozinhaRequestMapper;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscarOuFalha(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }

    @Transactional
    public Cozinha atualizar(Long id, CozinhaRequest cozinhaRequest) {
        Cozinha cozinha = buscarOuFalha(id);
        cozinhaRequestMapper.updateEntityFromDto(cozinhaRequest, cozinha);
        return salvar(cozinha);
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void excluir(Long cozinhaId) {

        if (!cozinhaRepository.existsById(cozinhaId)) {
            throw new CozinhaNaoEncontradaException(cozinhaId);
        }
        try {
            cozinhaRepository.deleteById(cozinhaId);
            cozinhaRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ENTIDADE_EM_USO, cozinhaId));
        }

    }


}
