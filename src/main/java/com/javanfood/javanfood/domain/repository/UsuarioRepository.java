package com.javanfood.javanfood.domain.repository;

import com.javanfood.javanfood.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
