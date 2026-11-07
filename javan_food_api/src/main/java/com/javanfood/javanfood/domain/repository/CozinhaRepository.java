package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.model.Cozinha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    Page<Cozinha> findByNomeContaining(String nome, Pageable pageable);


    Optional<Cozinha> findByNome(String nome);
}

