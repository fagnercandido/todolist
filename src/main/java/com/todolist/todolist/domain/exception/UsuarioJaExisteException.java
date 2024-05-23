package com.todolist.todolist.domain.exception;

public class UsuarioJaExisteException extends RuntimeException {
    public UsuarioJaExisteException(final String email) {
        super("Usuário com email " + email + " já existe");
    }
}
