package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRespository extends JpaRepository<Estado, Long> {

}
