package com.app.fragments.service;

import android.util.Log;
import com.app.fragments.data.entities.Observacao;
import com.app.fragments.data.entities.Melhoramento;
import com.app.fragments.data.entities.Caracteristica;
import com.app.fragments.data.dao.MelhoramentoManejoDao;
import com.app.fragments.data.entities.ManejoMelhoramento;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;

public class ManejoMelhoramentoService {
    private static final String TAG = "ManejoMelhoramentoService";

    private final MelhoramentoManejoDao melhoramentoManejoDao;
    private final MelhoramentoService melhoramentoService;
    private final CaracteristicaService caracteristicaService;
    private final ObservacaoService observacaoService;
    private final Executor executor;

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
        this.executor = Executors.newCachedThreadPool();
    }

    public CompletableFuture<List<ManejoMelhoramento>> getAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return melhoramentoManejoDao.getAll();
            } catch (Exception e) {
                Log.e(TAG, "Erro ao buscar todos os manejos", e);
                throw new RuntimeException("Erro ao carregar manejos", e);
            }
        }, executor);
    }

    public CompletableFuture<ManejoMelhoramento> getByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return melhoramentoManejoDao.findById(id);
            } catch (Exception e) {
                Log.e(TAG, "Erro ao buscar manejo por ID: " + id, e);
                throw new RuntimeException("Erro ao carregar manejo", e);
            }
        }, executor);
    }

    public CompletableFuture<Long> saveAsync(ManejoMelhoramento manejoMelhoramento) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return melhoramentoManejoDao.insert(manejoMelhoramento);
            } catch (Exception e) {
                Log.e(TAG, "Erro ao salvar manejo", e);
                throw new RuntimeException("Erro ao salvar manejo", e);
            }
        }, executor);
    }

    public CompletableFuture<Melhoramento> getMelhoramentoByIdAsync(Long id) {
        return melhoramentoService.getByIdAsync(id);
    }

    public CompletableFuture<Caracteristica> getCaracteristicaByIdAsync(Long id) {
        return caracteristicaService.getByIdAsync(id);
    }

    public CompletableFuture<Observacao> getObservacaoByIdAsync(Long id) {
        return observacaoService.getByIdAsync(id);
    }

    public CompletableFuture<List<Melhoramento>> getAllMelhoramentosAsync() {
        return melhoramentoService.getAllAsync();
    }

    public CompletableFuture<List<Caracteristica>> getAllCaracteristicasAsync() {
        return caracteristicaService.getAllAsync();
    }

    public CompletableFuture<List<Observacao>> getAllObservacoesAsync() {
        return observacaoService.getAllAsync();
    }


    public CompletableFuture<List<Long>> saveMultipleAsync(List<ManejoMelhoramento> manejos) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                List<Long> ids = new java.util.ArrayList<>();
                for (ManejoMelhoramento manejo : manejos) {
                    Long id = melhoramentoManejoDao.insert(manejo);
                    ids.add(id);
                }
                return ids;
            } catch (Exception e) {
                Log.e(TAG, "Erro ao salvar múltiplos manejos", e);
                throw new RuntimeException("Erro ao salvar manejos", e);
            }
        }, executor);
    }

}