package com.todolist.todolist.domain.service;


import com.todolist.todolist.domain.ports.out.repository.TarefasRepository;
import com.todolist.todolist.infrastructure.dto.TarefaRequestResponse;
import com.todolist.todolist.infrastructure.out.entity.Status;
import com.todolist.todolist.infrastructure.out.entity.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TarefasServiceTest {

    @Mock
    private TarefasRepository repository;

    @InjectMocks
    private TarefasService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void deveObterTodasAsTasksComSucesso() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Tarefa(), new Tarefa()));
        List<TarefaRequestResponse> responses = service.obterTodasAsTarefas();
        assertEquals(2, responses.size());
    }

    @Test
    void deveObterATaskPorIdComSucesso() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Tarefa()));
        TarefaRequestResponse response = service.obterTarefaPorId(1L);
        assertNotNull(response);
    }

    @Test
    void deveLancarExcecaoQuandoATaskNaoForEncontrada() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.obterTarefaPorId(1L));
    }

    @Test
    void deveRemoverATaskComSucesso() {
        doNothing().when(repository).deleteById(anyLong());
        service.removerTarefa(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }


    @Test
    void deveLancarExcecaoQuandoUpdateNaoEncontrarId() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        TarefaRequestResponse request = new TarefaRequestResponse(null, "titulo",
                "descricao", Instant.now(),
                Instant.now(),
                Status.CONCLUIDO);
        assertThrows(ResponseStatusException.class, () -> service.alterarTarefa(1L, request));
    }
}
