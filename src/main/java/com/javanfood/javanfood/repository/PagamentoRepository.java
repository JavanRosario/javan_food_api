package com.javanfood.javanfood.repository;

import com.javanfood.javanfood.domain.model.FormaPagamento;

import java.util.List;

public interface PagamentoRepository {
    List<FormaPagamento> listar();

    FormaPagamento findById(Long id);

    FormaPagamento adicionar(FormaPagamento pagamento);

    void delete(FormaPagamento pagamento);
}
