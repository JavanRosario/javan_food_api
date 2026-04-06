package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.exception.CozinhaNaoEncontradoException;
import com.javanfood.javanfood.domain.exception.EntidadeEmUsoException;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CozinhaService {

    private static final String MSG_ENTIDADE_EM_USO = "Cozinha de código: %d não pode ser removida, pois está em uso";
    private final CozinhaRepository cozinhaRepository;

    public Cozinha buscarOuFalha(Long cozinha_id) {
        return cozinhaRepository.findById(cozinha_id)
                .orElseThrow(() -> new CozinhaNaoEncontradoException(cozinha_id));
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void excluir(Long cozinhaId) {

        if (!cozinhaRepository.existsById(cozinhaId)) {
            throw new CozinhaNaoEncontradoException(cozinhaId);
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
