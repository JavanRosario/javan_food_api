package com.javanfood.javanfood.infraistructure.repository;

import com.javanfood.javanfood.domain.model.Cidade;
import com.javanfood.javanfood.api.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CidadeRepositoryJpa implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Cidade> listar() {
        TypedQuery<Cidade> query = entityManager.createQuery("from cidade", Cidade.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Cidade findById(Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Override
    @Transactional
    public Cidade adicionar(Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    @Transactional
    public void delete(Cidade cidade) {
        cidade = findById(cidade.getId());
        entityManager.remove(cidade);
    }


}
