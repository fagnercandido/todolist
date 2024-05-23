package com.todolist.todolist.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotNull(message = "Email é obrigatório e deve possuir no máximo 100 caracteres")
        @Size(max = 100)
        String email,
        @NotNull(message = "Senha é obrigatório e deve possuir no máximo 50 caracteres")
        @Size(max = 50)
        String senha) {
}
