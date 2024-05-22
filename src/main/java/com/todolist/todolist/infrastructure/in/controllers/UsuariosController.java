package com.todolist.todolist.infrastructure.in.controllers;

import com.todolist.todolist.domain.service.UsuariosService;
import com.todolist.todolist.infrastructure.dto.UsuarioRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService service;

    public UsuariosController(final UsuariosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> adicionarUsuario(final @RequestBody UsuarioRequest request) {
        service.salvarUsuario(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
