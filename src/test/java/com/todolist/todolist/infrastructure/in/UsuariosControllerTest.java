package com.todolist.todolist.infrastructure.in;

import com.todolist.todolist.domain.service.UsuariosService;
import com.todolist.todolist.infrastructure.dto.UsuarioRequest;
import com.todolist.todolist.infrastructure.in.controllers.UsuariosController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsuariosControllerTest {

    @Mock
    private UsuariosService service;

    @InjectMocks
    private UsuariosController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar200AoCriar() {
        UsuarioRequest request = new UsuarioRequest("nome", "email", "senha123");
        doNothing().when(service).salvarUsuario(any(UsuarioRequest.class));
        ResponseEntity<Object> response = controller.adicionarUsuario(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(service, times(1)).salvarUsuario(any(UsuarioRequest.class));
    }
}
