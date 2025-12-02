package com.javanfood.javanfood.infraistructure.repository;

import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class RestauranteRepositoryJpa implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Restaurante> listar() {
        TypedQuery<Restaurante> query = entityManager.createQuery("from Restaurante", Restaurante.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Restaurante findById(Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Override
    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    @Override
    @Transactional
    public void delete(Restaurante restaurante) {
        restaurante = findById(restaurante.getId());
        entityManager.remove(restaurante);
    }
}
