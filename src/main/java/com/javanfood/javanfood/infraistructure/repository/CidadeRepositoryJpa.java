package com.javanfood.javanfood.infraistructure.repository;

import com.javanfood.javanfood.api.repository.CidadeRepository;
import com.javanfood.javanfood.domain.model.Cidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CidadeRepositoryJpa implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        TypedQuery<Cidade> query = entityManager.createQuery("from Cidade", Cidade.class);
        return query.getResultList();
    }

    @Override
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
    public void delete(Long id) {
        Cidade cidade = findById(id);
        if (cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(cidade);
    }


}
