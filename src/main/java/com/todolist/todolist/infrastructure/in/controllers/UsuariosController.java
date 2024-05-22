package com.todolist.todolist.infrastructure.in.controllers;

import com.todolist.todolist.domain.ports.out.repository.UsuariosRepository;
import com.todolist.todolist.infrastructure.dto.UsuarioRequest;
import com.todolist.todolist.infrastructure.out.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosRepository repository;

    public UsuariosController(final UsuariosRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Object> adicionarUsuario(final @RequestBody UsuarioRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Usuario usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setNome(request.nome());
        usuario.setSenha(encoder.encode(request.senha()));

        this.repository.save(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
    }

}
