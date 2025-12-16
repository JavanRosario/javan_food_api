package com.javanfood.javanfood.domain.customRepository;

import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.RestauranteRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Restaurante> find(String nome, BigDecimal txFreteInicial, BigDecimal txFreteFinal) {

        Map<String, Object> parametros = new HashMap<>();

        var jqpl = new StringBuilder();
        jqpl.append("from Restaurante where 0=0 ");

        if (StringUtils.hasLength(nome)) {
            jqpl.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        if (txFreteInicial != null) {
            jqpl.append("and taxaFrete >= :txInicial ");
            parametros.put("txInicial", txFreteInicial);
        }
        if (txFreteFinal != null) {
            jqpl.append("and taxaFrete <= :txFinal ");
            parametros.put("txFinal", txFreteFinal);
        }

        TypedQuery<Restaurante> query = entityManager.createQuery(jqpl.toString(), Restaurante.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();

    }
}
