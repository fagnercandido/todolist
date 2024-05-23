package com.todolist.todolist.domain.service;

import com.todolist.todolist.domain.ports.out.repository.TarefasRepository;
import com.todolist.todolist.infrastructure.dto.TarefaRequestResponse;
import com.todolist.todolist.infrastructure.out.entity.Tarefa;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TarefasService {

    private final TarefasRepository repository;

    public TarefasService(final TarefasRepository repository) {
        this.repository = repository;
    }

    public void adicionarTarefa(TarefaRequestResponse tarefaRequest) {
        repository.save(new Tarefa(tarefaRequest.titulo(),
                tarefaRequest.descricao(),
                tarefaRequest.dataCriacao(),
                tarefaRequest.dataAtualizacao(),
                tarefaRequest.status()));
    }

    public List<TarefaRequestResponse> obterTodasAsTarefas() {
        final List<Tarefa> tarefas = repository.findAll();
        return tarefas.stream().map(tarefa -> new TarefaRequestResponse(tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getDataCriacao(),
                tarefa.getDataAtualiacao(),
                tarefa.getStatus())).toList();
    }

    public TarefaRequestResponse obterTarefaPorId(Long id) {
        return repository.findById(id).map(tarefa -> new TarefaRequestResponse(tarefa.getId(),
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getDataCriacao(),
                        tarefa.getDataAtualiacao(),
                        tarefa.getStatus()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada!"));
    }

    public void removerTarefa(Long id) {
        repository.deleteById(id);
    }

    public void alterarTarefa(Long id, TarefaRequestResponse tarefaRequest) {
        Tarefa tarefa = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada!"));
        tarefa.setTitulo(tarefaRequest.titulo());
        tarefa.setDescricao(tarefaRequest.descricao());
        tarefa.setStatus(tarefaRequest.status());
        tarefa.setDataCriacao(tarefaRequest.dataCriacao());
        tarefa.setDataAtualiacao(tarefaRequest.dataAtualizacao());
        repository.save(tarefa);
    }
}
