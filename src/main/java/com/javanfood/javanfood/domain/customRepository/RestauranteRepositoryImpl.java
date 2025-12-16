package com.javanfood.javanfood.domain.customRepository;

import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.RestauranteRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Restaurante> find(String nome, BigDecimal txFreteInicial, BigDecimal txFreteFinal) {

        var jqpl = "from Restaurante where nome like " +
                ":nome and taxaFrete between :txInicial and :txFinal";

        return entityManager.createQuery(jqpl, Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("txInicial", txFreteInicial)
                .setParameter("txFinal", txFreteFinal)
                .getResultList();
    }
}
