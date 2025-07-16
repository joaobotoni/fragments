package com.app.fragments.service;


import androidx.collection.MutableFloatList;

import com.app.fragments.data.dao.CaracteristicaDao;
import com.app.fragments.data.entities.Caracteristica;
import com.app.fragments.data.entities.Melhoramento;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CaracteristicaService {
    private final CaracteristicaDao dao;
    private final MelhoramentoService melhoramentoService;
    public CaracteristicaService(CaracteristicaDao dao, MelhoramentoService melhoramentoService) {
        this.dao = dao;
        this.melhoramentoService = melhoramentoService;
    }
    public List<Caracteristica> getAllAsync() {
        CompletableFuture<List<Caracteristica>> future = CompletableFuture.supplyAsync(() -> dao.getAll().parallelStream().collect(Collectors.toList()));
        return future.join();
    }
    public Caracteristica getByIdAsync(Long id) {
        CompletableFuture<Caracteristica> future = CompletableFuture.supplyAsync(() -> dao.findById(id));
        return future.join();
    }
    public Melhoramento getMelhoramentoByIdAsync(Long id){
        CompletableFuture<Melhoramento> future = CompletableFuture.supplyAsync(() -> melhoramentoService.getByIdAsync(id));
        return future.join();
    }
}