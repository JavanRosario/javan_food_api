package com.javanfood.javanfood.infraistructure.repository;

import com.javanfood.javanfood.domain.model.Endereco;
import com.javanfood.javanfood.api.repository.EnderecoRespository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EnderecoRepositoryJpa implements EnderecoRespository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Endereco> listar() {
        TypedQuery<Endereco> query = entityManager.createQuery("from Endereco", Endereco.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Endereco findById(Long id) {
        return entityManager.find(Endereco.class, id);
    }

    @Override
    @Transactional
    public Endereco adicionar(Endereco cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    @Transactional
    public void delete(Endereco cidade) {
        cidade = findById(cidade.getId());
        entityManager.remove(cidade);
    }


}
