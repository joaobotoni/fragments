package com.app.fragments.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.fragments.R;
import com.app.fragments.data.entities.Caracteristica;
import com.app.fragments.data.entities.Melhoramento;
import com.app.fragments.data.entities.Observacao;
import com.app.fragments.service.ManejoMelhoramentoService;
import com.app.fragments.ui.adapter.FormsXgpManejoMelhoramentoAdapter;
import com.app.fragments.ui.adapter.ObservacaoAdapter;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;
import com.app.fragments.ui.components.ObservacaoComponent;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class XgpManejoMelhoramentoFragment extends Fragment {

    private ManejoMelhoramentoService manejoMelhoramentoService;
    private Handler mainHandler;
    private RecyclerView recyclerView;
    private AutoCompleteTextView observacaoAutoCompleteTextView;
    private MaterialButton saved;
    private String nota;
    private String observacaoSelecionada;

    public XgpManejoMelhoramentoFragment(ManejoMelhoramentoService service) {
        super(R.layout.fragment_xgp_manejo_melhoramento);
        this.manejoMelhoramentoService = service;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        loadAllData();
    }

   // Inicializa todos os elementos da interface
    private void setupViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);
        observacaoAutoCompleteTextView = view.findViewById(R.id.spinner_manejo_melhoramento);
        saved = view.findViewById(R.id.send);
        mainHandler = new Handler(Looper.getMainLooper());
    }

    // Carrega dados dos formulários e observações de forma assíncrona e aplica os adapters
    private void loadAllData() {
        CompletableFuture<List<FormsXgpManejoMelhoramentoComponent>> formsFuture = loadFormsAsync();
        CompletableFuture<List<ObservacaoComponent>> observacoesFuture = loadObservacoesAsync();

        CompletableFuture.allOf(formsFuture, observacoesFuture)
                .thenAcceptAsync(ignored -> {
                    List<FormsXgpManejoMelhoramentoComponent> forms = formsFuture.join();
                    List<ObservacaoComponent> observacoes = observacoesFuture.join();
                    mainHandler.post(() -> {
                        setupRecyclerView(forms);
                        setupObservacaoSpinner(observacoes);
                    });
                })
                .exceptionally(throwable -> {
                    mainHandler.post(() -> showErrorMessage("Erro ao carregar dados: " + throwable.getMessage()));
                    return null;
                });
    }

    // Busca os formulários de forma assíncrona usando CompletableFuture
    private CompletableFuture<List<FormsXgpManejoMelhoramentoComponent>> loadFormsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                List<Caracteristica> caracteristicas = manejoMelhoramentoService.getAllCaracteristicasAsync();
                List<CompletableFuture<FormsXgpManejoMelhoramentoComponent>> futures = caracteristicas.stream()
                        .map(caracteristica -> CompletableFuture.supplyAsync(() -> {
                            try {
                                Melhoramento melhoramento = manejoMelhoramentoService.getMelhoramentoByIdAsync(caracteristica.getIdMelhoramento());
                                if (melhoramento != null) {
                                    return new FormsXgpManejoMelhoramentoComponent(
                                            melhoramento.getNome(),
                                            caracteristica.getSigla(),
                                            getCharacteristicExceptionByNoteRange(caracteristica.getNotaInicial(), caracteristica.getNotaFinal(), caracteristica.getExcessao())
                                    );
                                }
                            } catch (Exception e) {
                                System.err.println("Erro ao carregar melhoramento para característica " + caracteristica.getIdCaracteristica() + ": " + e.getMessage());
                            }
                            return null;
                        }))
                        .collect(Collectors.toList());

                return futures.stream()
                        .map(CompletableFuture::join)
                        .filter(java.util.Objects::nonNull)
                        .collect(Collectors.toList());

            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar formulários: " + e.getMessage(), e);
            }
        });
    }

   // Busca observações de forma assíncrona usando CompletableFuture
    private CompletableFuture<List<ObservacaoComponent>> loadObservacoesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                List<Observacao> observacoesList = manejoMelhoramentoService.getAllObservacoesAsync();
                return observacoesList.stream()
                        .map(obs -> new ObservacaoComponent(obs.getSigla()))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar observações: " + e.getMessage(), e);
            }
        });
    }

    // Aplica e configura o adapter dos formulários no RecyclerView
    private void setupRecyclerView(List<FormsXgpManejoMelhoramentoComponent> forms) {
        FormsXgpManejoMelhoramentoAdapter adapter = new FormsXgpManejoMelhoramentoAdapter(requireContext(), forms);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }

    // Aplica e configura o adapter das observações no AutoCompleteTextView
    private void setupObservacaoSpinner(List<ObservacaoComponent> observacoes) {
        ObservacaoAdapter adapter = new ObservacaoAdapter(requireContext(), observacoes);
        observacaoAutoCompleteTextView.setAdapter(adapter);
        observacaoAutoCompleteTextView.setOnItemClickListener((parent, itemView, position, id) -> {
            ObservacaoComponent selected = (ObservacaoComponent) parent.getItemAtPosition(position);
            observacaoSelecionada = selected.getObservacao();
        });
    }

    private String getCharacteristicExceptionByNoteRange(Integer initialNote, Integer finalNote, String exception) {
        if (initialNote == null || finalNote == null) {
            Toast.makeText(getContext(), "Nota inicial ou final está nula", Toast.LENGTH_SHORT).show();
            throw new IllegalArgumentException("Nota inicial ou final não pode ser nula");
        }

        if (exception == null || exception.trim().isEmpty()) {
            Toast.makeText(getContext(), "A exceção não pode ser nula ou vazia", Toast.LENGTH_SHORT).show();
            throw new IllegalArgumentException("A exceção não pode ser nula ou vazia");
        }

        if (initialNote < 1 || finalNote > 6 || initialNote >= finalNote) {
            Toast.makeText(getContext(), "Intervalo de nota inválido. Esperado: 1 a 6", Toast.LENGTH_SHORT).show();
            throw new IllegalArgumentException("Intervalo de nota inválido");
        }

        return exception;
    }

    private void showErrorMessage(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}

