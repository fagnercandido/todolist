package com.todolist.todolist.domain.ports.out.repository;

import com.todolist.todolist.infrastructure.out.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
