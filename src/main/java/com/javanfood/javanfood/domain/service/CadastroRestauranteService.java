package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.repository.PagamentoRepository;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.FormaPagamento;
import com.javanfood.javanfood.domain.model.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    PagamentoRepository pagamentoRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaExeption(
                "Não existe cadastro de cozinha com código %d".formatted(cozinhaId)));

//        Long pagamentoId = restaurante.getFormaPagamento().getId();
//        FormaPagamento formaPagamento = pagamentoRepository.findById(pagamentoId)
//                .orElseThrow(() -> new EntidadeNaoEncontradaExeption(
//                        String.format("Não existe cadastro de pagamento com código: %d", pagamentoId)));


        restaurante.setCozinha(cozinha);
//        restaurante.setFormaPagamento(formaPagamento);
        return restauranteRepository.save(restaurante);
    }
}

