package com.todolist.todolist.domain.service;

import com.todolist.todolist.domain.exception.UsuarioJaExisteException;
import com.todolist.todolist.domain.ports.out.repository.UsuariosRepository;
import com.todolist.todolist.infrastructure.dto.UsuarioRequest;
import com.todolist.todolist.infrastructure.out.entity.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuariosService {

    private final UsuariosRepository repository;

    public UsuariosService(final UsuariosRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(final UsuarioRequest request) {
        validarUsuarioExistente(request);
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final Usuario usuario = new Usuario(request.nome(), request.email(), encoder.encode(request.senha()), "ROLE_USER");
        this.repository.save(usuario);
    }

    private void validarUsuarioExistente(final UsuarioRequest request) {
        this.repository.findByEmail(request.email())
                .ifPresent(usuario -> {
                    throw new UsuarioJaExisteException(request.email());
                });
    }
}
