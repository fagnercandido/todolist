package com.todolist.todolist.domain.service;

import com.todolist.todolist.domain.exception.UsuarioJaExisteException;
import com.todolist.todolist.domain.ports.out.repository.UsuariosRepository;
import com.todolist.todolist.infrastructure.dto.UsuarioRequest;
import com.todolist.todolist.infrastructure.out.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UsuariosServiceTest {

    @Mock
    private UsuariosRepository repository;

    @InjectMocks
    private UsuariosService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarUmaExcecaoSeUsuarioExistir() {
        UsuarioRequest request = new UsuarioRequest("test", "test@test.com", "password");
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(new Usuario()));
        assertThrows(UsuarioJaExisteException.class, () -> service.salvarUsuario(request));
    }
}
