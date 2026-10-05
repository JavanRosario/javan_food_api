package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.model.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByCodigo(String codigo);

    @EntityGraph(attributePaths = {
            "usuario",
            "restaurante",
            "restaurante.cozinha",
            "itemPedido",
            "itemPedido.produto"
    })
    @Query("SELECT DISTINCT p FROM Pedido p")
    List<Pedido> findAll();
}
