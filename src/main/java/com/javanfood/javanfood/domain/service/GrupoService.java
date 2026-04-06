package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.api.dto.request.GrupoRequest;
import com.javanfood.javanfood.api.mapper.grupoMapper.GrupoRequestMapper;
import com.javanfood.javanfood.domain.exception.EntidadeEmUsoException;
import com.javanfood.javanfood.domain.exception.GrupoNãoEncontradoException;
import com.javanfood.javanfood.domain.model.Grupo;
import com.javanfood.javanfood.domain.repository.GrupoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GrupoService {
    private static final String MSG_ENTIDADE_EM_USO = "Grupo de código: %d não pode ser removida, pois está em uso";
    private final GrupoRepository grupoRepository;
    private final GrupoRequestMapper grupoRequestMapper;

    public Grupo buscaOuFalha(Long grupoId) {
        return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNãoEncontradoException(grupoId));
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public Grupo atualizar(Long id, GrupoRequest grupoRequest) {
        Grupo grupo = buscaOuFalha(id);
        grupoRequestMapper.updateEntityFromDto(grupoRequest, grupo);
        return salvar(grupo);
    }

    @Transactional
    public void deletar(Long id) {
        if (!grupoRepository.existsById(id)) {
            throw new GrupoNãoEncontradoException(id);
        }
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, id));
        }
    }
}
