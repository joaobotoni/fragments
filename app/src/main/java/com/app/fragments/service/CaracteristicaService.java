package com.app.fragments.service;

import com.app.fragments.data.dao.CaracteristicaDao;
import com.app.fragments.data.entities.Caracteristica;
import com.app.fragments.data.entities.Melhoramento;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CaracteristicaService {
    private final CaracteristicaDao dao;
    private final MelhoramentoService melhoramentoService;

    public CaracteristicaService(CaracteristicaDao dao, MelhoramentoService melhoramentoService) {
        this.dao = dao;
        this.melhoramentoService = melhoramentoService;
    }
    public CompletableFuture<List<Caracteristica>> getAllAsync() {
        return CompletableFuture.supplyAsync(dao::getAll);
    }

    public CompletableFuture<Caracteristica> getByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> dao.findById(id));
    }

    public CompletableFuture<Melhoramento> getMelhoramentoByIdAsync(Long id) {
        return melhoramentoService.getByIdAsync(id);
    }
}
