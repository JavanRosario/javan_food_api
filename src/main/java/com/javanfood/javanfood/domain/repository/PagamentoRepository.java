package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
