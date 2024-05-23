package com.todolist.todolist.infrastructure.dto;

import com.todolist.todolist.infrastructure.out.entity.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record TarefaRequestResponse(
        Long id,
        @NotNull(message = "Título é obrigatório e deve possuir no máximo 100 caracteres")
        @Size(max = 100)
        String titulo,
        @NotNull(message = "Descrição é obrigatório e deve possuir no máximo 500 caracteres")
        @Size(max = 500)
        String descricao,
        @NotNull(message = "Data de Criação é obrigatório")
        Instant dataCriacao,
        @NotNull(message = "Data de Atualização é obrigatório")
        Instant dataAtualizacao,
        @NotNull(message = "Status é obrigatório")
        Status status) {
}
