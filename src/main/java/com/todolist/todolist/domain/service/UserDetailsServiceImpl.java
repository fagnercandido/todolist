package com.todolist.todolist.domain.service;

import com.todolist.todolist.domain.ports.out.repository.UsuariosRepository;
import com.todolist.todolist.infrastructure.out.entity.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuariosRepository repository;

    public UserDetailsServiceImpl(final UsuariosRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final Optional<Usuario> optionalUsuario = this.repository.findByEmail(email);
        if (optionalUsuario.isEmpty()) {
            throw new UsernameNotFoundException("O usuário informado não existe " + email);
        }
        final Usuario usuario = optionalUsuario.get();
        return User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}