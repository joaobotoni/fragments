package com.app.fragments.service;

import com.app.fragments.data.dao.MelhoramentoManejoDao;
import com.app.fragments.data.entities.Caracteristica;
import com.app.fragments.data.entities.ManejoMelhoramento;
import com.app.fragments.data.entities.Melhoramento;
import com.app.fragments.data.entities.Observacao;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ManejoMelhoramentoService {

    private final MelhoramentoManejoDao melhoramentoManejoDao;
    private final MelhoramentoService melhoramentoService;
    private final CaracteristicaService caracteristicaService;
    private final ObservacaoService observacaoService;

    public ManejoMelhoramentoService(
            MelhoramentoManejoDao dao,
            MelhoramentoService melhoramentoService,
            CaracteristicaService caracteristicaService,
            ObservacaoService observacaoService
    ) {
        this.melhoramentoManejoDao = dao;
        this.melhoramentoService = melhoramentoService;
        this.caracteristicaService = caracteristicaService;
        this.observacaoService = observacaoService;
    }

    public List<ManejoMelhoramento> getAllAsync() {
        CompletableFuture<List<ManejoMelhoramento>> future = CompletableFuture.supplyAsync(() ->
                melhoramentoManejoDao.getAll().parallelStream().collect(Collectors.toList()));
        return future.join();
    }

    public ManejoMelhoramento getByIdAsync(Long id) {
        CompletableFuture<ManejoMelhoramento> future = CompletableFuture.supplyAsync(() ->
                melhoramentoManejoDao.findById(id));
        return future.join();
    }

    public long saveAsync(ManejoMelhoramento manejoMelhoramento) {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() ->
                melhoramentoManejoDao.insert(manejoMelhoramento));
        return future.join();
    }

    public Melhoramento getMelhoramentoByIdAsync(Long id) {
        CompletableFuture<Melhoramento> future = CompletableFuture.supplyAsync(() ->
                melhoramentoService.getByIdAsync(id));
        return future.join();
    }

    public List<Melhoramento> getAllMelhoramentosAsync() {
        CompletableFuture<List<Melhoramento>> future = CompletableFuture.supplyAsync(() ->
                melhoramentoService.getAllAsync().parallelStream().collect(Collectors.toList()));
        return future.join();
    }

    public Caracteristica getCaracteristicaByIdAsync(Long id) {
        CompletableFuture<Caracteristica> future = CompletableFuture.supplyAsync(() ->
                caracteristicaService.getByIdAsync(id));
        return future.join();
    }

    public List<Caracteristica> getAllCaracteristicasAsync() {
        CompletableFuture<List<Caracteristica>> future = CompletableFuture.supplyAsync(() ->
                caracteristicaService.getAllAsync().parallelStream().collect(Collectors.toList()));
        return future.join();
    }

    public List<Observacao> getAllObservacoesAsync() {
        CompletableFuture<List<Observacao>> future = CompletableFuture.supplyAsync(() ->
                observacaoService.getAllAsync().parallelStream().collect(Collectors.toList()));
        return future.join();
    }

    public Observacao getObservacaoByIdAsync(Long id)  {
        CompletableFuture<Observacao> future = CompletableFuture.supplyAsync(() ->
                observacaoService.getByIdAsync(id));
        return future.join();
    }
}
