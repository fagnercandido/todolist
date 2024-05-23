package com.todolist.todolist.domain.service;

import com.todolist.todolist.domain.ports.out.repository.UsuariosRepository;
import com.todolist.todolist.infrastructure.configuration.UserDetailsImpl;
import com.todolist.todolist.infrastructure.out.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        return UserDetailsImpl.build(usuario);
    }

}