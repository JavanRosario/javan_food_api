package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.model.Permisao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisaoRepository extends JpaRepository<Permisao, Long> {
}
