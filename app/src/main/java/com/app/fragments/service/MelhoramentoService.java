package com.app.fragments.service;


import com.app.fragments.data.dao.MelhoramentoDao;
import com.app.fragments.data.entities.Melhoramento;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MelhoramentoService {

    private final MelhoramentoDao dao;

    public MelhoramentoService(MelhoramentoDao dao) {
        this.dao = dao;
    }

    public List<Melhoramento> getAllAsync() {
        CompletableFuture<List<Melhoramento>> future = CompletableFuture.supplyAsync(() -> dao.getAll().parallelStream().collect(Collectors.toList()));
        return future.join();
    }

    public Melhoramento getByIdAsync(Long id) {
        CompletableFuture<Melhoramento> future = CompletableFuture.supplyAsync(() -> dao.findById(id));
        return future.join();
    }
}