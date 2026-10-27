package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.customRepository.RestauranteRepositoryQueries;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.projection.RestauranteNomeProjection;
import com.javanfood.javanfood.domain.repository.projection.RestauranteResumidoProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal txInicial, BigDecimal txFinal);

    @Query("select distinct r from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAllWithCozinhaFormaPagamentoList();

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

    Optional<Restaurante> findFirstByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    boolean existsByNome(String nome);

    int countByCozinhaId(Long cozinhaId);

    @EntityGraph(attributePaths = {"cozinha", "endereco.cidade", "endereco.cidade.estado"})
    @Query("SELECT DISTINCT r FROM Restaurante r order by r.id asc")
    List<Restaurante> findAllCompleto();

    @Query("select r.nome as nome, r.id as id from Restaurante r")
    List<RestauranteNomeProjection> findAllByNome();

    @Query("select r.id as id, r.nome as nome, r.taxaFrete as taxaFrete, c as cozinha " +
            "from Restaurante r " +
            "inner join r.cozinha c " +
            "order by r.id asc")
    List<RestauranteResumidoProjection> findAllResumido();


}
