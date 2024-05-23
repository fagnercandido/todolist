package com.todolist.todolist.infrastructure.out.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Table(name = "tarefas")
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @Column(name = "data_criacao")
    private Instant dataCriacao;
    @Column(name = "data_atualizacao")
    private Instant dataAtualiacao;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Tarefa(String titulo, String descricao, Instant dataCriacao, Instant dataAtualiacao, Status status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataAtualiacao = dataAtualiacao;
        this.status = status;
    }

    public Tarefa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Instant getDataAtualiacao() {
        return dataAtualiacao;
    }

    public void setDataAtualiacao(Instant dataAtualiacao) {
        this.dataAtualiacao = dataAtualiacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
