package com.javanfood.javanfood.infraistructure.repository;

import com.javanfood.javanfood.domain.model.FormaPagamento;
import com.javanfood.javanfood.api.repository.PagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PagamentoRepositoryJpa implements PagamentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public FormaPagamento adicionar(FormaPagamento pagamento) {
        return entityManager.merge(pagamento);
    }

    @Override
    @Transactional
    public FormaPagamento findById(Long id) {
        return entityManager.find(FormaPagamento.class, id);
    }

    @Override
    @Transactional
    public List<FormaPagamento> listar() {
        TypedQuery<FormaPagamento> query = entityManager
                .createQuery("from forma_pagamento", FormaPagamento.class);
        return query.getResultList();
    }

    @Override
    public void delete(FormaPagamento pagamento) {
        pagamento = findById(pagamento.getId());
        entityManager.remove(pagamento);
    }
}
