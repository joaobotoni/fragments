package com.app.fragments.service;


import com.app.fragments.data.dao.ObservacaoDao;
import com.app.fragments.data.entities.Melhoramento;
import com.app.fragments.data.entities.Observacao;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ObservacaoService {
    private final ObservacaoDao dao;
    private final MelhoramentoService melhoramentoService;

    public ObservacaoService(ObservacaoDao dao, MelhoramentoService melhoramentoService) {
        this.dao = dao;
        this.melhoramentoService = melhoramentoService;
    }

    public List<Observacao> getAllAsync() {
        CompletableFuture<List<Observacao>> future = CompletableFuture.supplyAsync(() -> dao.getAll().parallelStream().collect(Collectors.toList()));
        return future.join();
    }

    public Observacao getByIdAsync(Long id) {
        CompletableFuture<Observacao> future = CompletableFuture.supplyAsync(() -> dao.findById(id));
        return future.join();
    }

    public Melhoramento getMelhoramentoByIdAsync(Long id) {
        CompletableFuture<Melhoramento> future = CompletableFuture.supplyAsync(() -> melhoramentoService.getByIdAsync(id));
        return future.join();
    }
}