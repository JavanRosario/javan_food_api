package com.javanfood.javanfood.infraistructure.repository;

import com.javanfood.javanfood.domain.model.Permisao;
import com.javanfood.javanfood.api.repository.PermisaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PermicaoRepositoryJpa implements PermisaoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Permisao> listar() {
        TypedQuery<Permisao> query = entityManager.createQuery("from permicao", Permisao.class);
        return query.getResultList();
    }

    @Override
    public Permisao findById(Long id) {
        return entityManager.find(Permisao.class, id);
    }

    @Override
    public Permisao adicionar(Permisao permisao) {
        return entityManager.merge(permisao);
    }

    @Override
    public void delete(Permisao permisao) {
        permisao = findById(permisao.getId());
        entityManager.remove(permisao);
    }
}
