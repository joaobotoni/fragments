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

    public CompletableFuture<List<ManejoMelhoramento>> getAllAsync() {
        return CompletableFuture.supplyAsync(melhoramentoManejoDao::getAll);
    }

    public CompletableFuture<ManejoMelhoramento> getByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() ->
                melhoramentoManejoDao.findById(id));
    }

    public CompletableFuture<Long> saveAsync(ManejoMelhoramento manejoMelhoramento) {
        return CompletableFuture.supplyAsync(() ->
                melhoramentoManejoDao.insert(manejoMelhoramento));
    }

    public CompletableFuture<Melhoramento> getMelhoramentoByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() ->
                melhoramentoService.getByIdAsync(id));
    }


    public CompletableFuture<Caracteristica> getCaracteristicaByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() ->
                caracteristicaService.getByIdAsync(id));
    }
    public CompletableFuture<Observacao> getObservacaoByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() ->
                observacaoService.getByIdAsync(id));
    }

    public CompletableFuture<List<Melhoramento>> getAllMelhoramentosAsync() {
        return CompletableFuture.supplyAsync(melhoramentoService::getAllAsync);
    }
    public CompletableFuture<List<Caracteristica>> getAllCaracteristicasAsync() {
        return CompletableFuture.supplyAsync(caracteristicaService::getAllAsync);
    }

    public CompletableFuture<List<Observacao>> getAllObservacoesAsync() {
        return CompletableFuture.supplyAsync(observacaoService::getAllAsync);
    }

}
