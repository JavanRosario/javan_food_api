package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.exception.ProdutoNaoEncontradoException;
import com.javanfood.javanfood.domain.model.Produto;
import com.javanfood.javanfood.domain.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto buscarOuFalha(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }
}
