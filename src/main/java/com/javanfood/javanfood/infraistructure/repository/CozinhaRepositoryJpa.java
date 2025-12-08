package com.javanfood.javanfood.infraistructure.repository;

import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.api.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

 @Component
public class CozinhaRepositoryJpa implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Cozinha> listar() {
        TypedQuery<Cozinha> query = entityManager.createQuery("from Cozinha", Cozinha.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Cozinha adicionar(Cozinha cozinha) {
        return entityManager.merge(cozinha);

    }

    @Transactional
    @Override
    public void delete(Cozinha cozinha) {
        cozinha = findById(cozinha.getId());
        entityManager.remove(cozinha);
    }

    @Transactional
    @Override
    public Cozinha findById(Long id) {
        return entityManager.find(Cozinha.class, id);
    }
}
