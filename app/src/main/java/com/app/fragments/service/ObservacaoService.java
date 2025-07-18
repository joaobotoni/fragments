package com.app.fragments.service;

import com.app.fragments.data.dao.ObservacaoDao;
import com.app.fragments.data.entities.Melhoramento;
import com.app.fragments.data.entities.Observacao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ObservacaoService {
    private final ObservacaoDao dao;
    private final MelhoramentoService melhoramentoService;

    public ObservacaoService(ObservacaoDao dao, MelhoramentoService melhoramentoService) {
        this.dao = dao;
        this.melhoramentoService = melhoramentoService;
    }

    public CompletableFuture<List<Observacao>> getAllAsync() {
        return CompletableFuture.supplyAsync(dao::getAll);
    }

    public CompletableFuture<Observacao> getByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> dao.findById(id));
    }

    public CompletableFuture<Melhoramento> getMelhoramentoByIdAsync(Long id) {
        return melhoramentoService.getByIdAsync(id);
    }
}
