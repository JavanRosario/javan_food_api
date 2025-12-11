package com.javanfood.javanfood.infraistructure.repository;

import com.javanfood.javanfood.domain.model.Estado;
import com.javanfood.javanfood.api.repository.EstadoRespository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EstadoRepositoryJpa implements EstadoRespository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> listar() {
        TypedQuery<Estado> query = entityManager.createQuery("from Estado", Estado.class);
        return query.getResultList();
    }

    @Override
    public Estado findById(Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Override
    @Transactional
    public Estado adicionar(Estado cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Estado estado = findById(id);
        if (estado == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(estado);
    }


}
