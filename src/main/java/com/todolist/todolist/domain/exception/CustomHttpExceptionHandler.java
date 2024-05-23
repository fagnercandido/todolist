package com.todolist.todolist.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;


@ControllerAdvice
public class CustomHttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UsuarioJaExisteException.class})
    public ResponseEntity<Object> handleBadRequestException(UsuarioJaExisteException ex) {
        final Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", Instant.now());
        responseBody.put("errors", ex.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

}
