package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.exception.ProdutoNaoEncontradoException;
import com.javanfood.javanfood.domain.model.Produto;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto buscarOuFalha(Long produtoId) {
        return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }

    public List<Produto> listarRestauranteAtivo(Restaurante restaurante) {
        return produtoRepository.findAtivosByRestaurante(restaurante);
    }

    public List<Produto> listarRestauranteInativo(Restaurante restaurante) {
        return produtoRepository.findInativosByRestaurante(restaurante);
    }
}
