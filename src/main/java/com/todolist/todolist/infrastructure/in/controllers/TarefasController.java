package com.todolist.todolist.infrastructure.in.controllers;

import com.todolist.todolist.domain.service.TarefasService;
import com.todolist.todolist.infrastructure.dto.TarefaRequestResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService service;

    public TarefasController(final TarefasService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<TarefaRequestResponse>> obterTodasAsTarefas() {
        return new ResponseEntity<>(service.obterTodasAsTarefas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<TarefaRequestResponse> obterTarefaPorId(@Valid @PathVariable(name = "id") final Long id) {
        return new ResponseEntity<>(service.obterTarefaPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> adicionarTarefas(final @Valid @RequestBody TarefaRequestResponse tarefa) {
        service.adicionarTarefa(tarefa);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> removerTarefa(@Valid @PathVariable(name = "id") final Long id) {
        service.removerTarefa(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> updateUser(@Valid @PathVariable("id") Long id, @RequestBody TarefaRequestResponse tarefa) {
        service.alterarTarefa(id, tarefa);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
