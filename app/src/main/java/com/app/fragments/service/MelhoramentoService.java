package com.app.fragments.service;

import com.app.fragments.data.dao.MelhoramentoDao;
import com.app.fragments.data.entities.Melhoramento;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MelhoramentoService {

    private final MelhoramentoDao dao;

    public MelhoramentoService(MelhoramentoDao dao) {
        this.dao = dao;
    }

    public CompletableFuture<List<Melhoramento>> getAllAsync() {
        return CompletableFuture.supplyAsync(dao::getAll);
    }

    public CompletableFuture<Melhoramento> getByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> dao.findById(id));
    }
}
