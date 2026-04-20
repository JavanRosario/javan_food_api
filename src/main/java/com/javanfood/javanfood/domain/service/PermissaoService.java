package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.exception.PermissaoNaoEncontradaException;
import com.javanfood.javanfood.domain.model.Permissao;
import com.javanfood.javanfood.domain.repository.PermissaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissaoService {
    private final PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalha(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}
