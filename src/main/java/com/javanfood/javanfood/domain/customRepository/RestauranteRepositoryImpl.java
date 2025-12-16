package com.javanfood.javanfood.domain.customRepository;

import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.RestauranteRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Restaurante> find(String nome, BigDecimal txFreteInicial, BigDecimal txFreteFinal) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteriaQuery = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);


        Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");
        Predicate taxaMenorOuIgual = builder.greaterThanOrEqualTo(root.get("taxa_frete"), txFreteInicial);
        Predicate taxaMenorOuIgual2 = builder.greaterThanOrEqualTo(root.get("taxa_frete"), txFreteFinal);


        criteriaQuery.where(taxaMenorOuIgual,taxaMenorOuIgual2);

        TypedQuery<Restaurante> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();

    }
}
