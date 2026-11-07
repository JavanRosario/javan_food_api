package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

    Optional<Pedido> findByCodigo(String codigo);

    @EntityGraph(attributePaths = {
            "usuario",
            "restaurante",
            "restaurante.cozinha",
            "itemPedido",
            "itemPedido.produto"
    })
    @Query("SELECT DISTINCT p FROM Pedido p")
    Page<Pedido> findAll(Specification specification, Pageable pageable);
}
