package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.model.Cidade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
 public static final List<Cidade> findByNameCidades = null;
 public static final List<Cidade> findByNameCidades2 = null;
 public static final List<Cidade> findByNameCidades3 = null;
}
