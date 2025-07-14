package com.app.fragments.ui.fragment;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.data.entities.Caracteristica;
import com.app.fragments.data.entities.Observacao;
import com.app.fragments.service.ManejoMelhoramentoService;
import com.app.fragments.ui.adapter.FormsXgpManejoMelhoramentoAdapter;
import com.app.fragments.ui.adapter.ObservacaoAdapter;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;
import com.app.fragments.ui.components.ObservacaoComponent;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class XgpManejoMelhoramentoFragment extends Fragment {

    private static final int MIN_NOTE_VALUE = 1;
    private static final int MAX_NOTE_VALUE = 6;
    private final ManejoMelhoramentoService manejoMelhoramentoService;
    private final Handler mainHandler;
    private final ExecutorService backgroundExecutor;
    private RecyclerView formsRecyclerView;
    private AutoCompleteTextView observationInput;
    private MaterialButton saveButton;
    private String selectedObservation;

    public XgpManejoMelhoramentoFragment(ManejoMelhoramentoService service) {
        super(R.layout.fragment_xgp_manejo_melhoramento);
        this.manejoMelhoramentoService = service;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.backgroundExecutor = Executors.newCachedThreadPool();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        loadInitialData();
        setupSaveButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        backgroundExecutor.shutdown();
    }

    private void initializeViews(View view) {
        formsRecyclerView = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);
        observationInput = view.findViewById(R.id.spinner_manejo_melhoramento);
        saveButton = view.findViewById(R.id.send);
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(v -> saveFormData());
    }

    private void saveFormData() {
        showSuccessMessage("Dados salvos com sucesso!");
    }

    private void loadInitialData() {
        CompletableFuture<List<FormsXgpManejoMelhoramentoComponent>> formsFuture = loadFormsAsync();
        CompletableFuture<List<ObservacaoComponent>> observationsFuture = loadObservationsAsync();

        CompletableFuture.allOf(formsFuture, observationsFuture)
                .thenAcceptAsync(ignored -> {
                    List<FormsXgpManejoMelhoramentoComponent> forms = formsFuture.join();
                    List<ObservacaoComponent> observations = observationsFuture.join();
                    mainHandler.post(() -> {
                        setupFormsRecyclerView(forms);
                        setupObservationInput(observations);
                    });
                }, backgroundExecutor)
                .exceptionally(throwable -> {
                    handleError("Erro ao carregar dados iniciais.", throwable);
                    return null;
                });
    }

    private CompletableFuture<List<FormsXgpManejoMelhoramentoComponent>> loadFormsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Caracteristica> characteristics = manejoMelhoramentoService.getAllCaracteristicasAsync();
            List<FormsXgpManejoMelhoramentoComponent> formComponents = new ArrayList<>();
            for (Caracteristica caracteristica : characteristics) {
                formComponents.add(new FormsXgpManejoMelhoramentoComponent(
                        caracteristica.getDescricao(),
                        caracteristica.getSigla(),
                        null
                ));
            }
            return formComponents;
        }, backgroundExecutor);
    }

    private CompletableFuture<List<ObservacaoComponent>> loadObservationsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<Observacao> observations = manejoMelhoramentoService.getAllObservacoesAsync();
            List<ObservacaoComponent> observationComponents = new ArrayList<>();
            for (Observacao observacao : observations) {
                observationComponents.add(new ObservacaoComponent(observacao.getSigla()));
            }
            return observationComponents;
        }, backgroundExecutor);
    }

    private void setupFormsRecyclerView(List<FormsXgpManejoMelhoramentoComponent> forms) {
        FormsXgpManejoMelhoramentoAdapter adapter = new FormsXgpManejoMelhoramentoAdapter(requireContext(), forms);
        formsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        formsRecyclerView.setAdapter(adapter);
    }

    private void setupObservationInput(List<ObservacaoComponent> observations) {
        ObservacaoAdapter adapter = new ObservacaoAdapter(requireContext(), observations);
        observationInput.setAdapter(adapter);
        observationInput.setOnItemClickListener((parent, view, position, id) -> {
            ObservacaoComponent selected = (ObservacaoComponent) parent.getItemAtPosition(position);
            selectedObservation = selected.getObservacao();
        });
    }

    private String validateNoteRangeAndException(Integer initialNote, Integer finalNote, String exception) {
        if (initialNote == null || finalNote == null) {
            throw new IllegalArgumentException("Notas inicial e final não podem ser nulas.");
        }
        if (exception == null || exception.trim().isEmpty()) {
            throw new IllegalArgumentException("A exceção não pode ser nula ou vazia.");
        }
        if (initialNote < MIN_NOTE_VALUE || finalNote > MAX_NOTE_VALUE || initialNote >= finalNote) {
            throw new IllegalArgumentException("Intervalo de nota inválido. Esperado: " + MIN_NOTE_VALUE + " a " + MAX_NOTE_VALUE + ".");
        }
        return exception;
    }

    private void handleError(String userMessage, Throwable throwable) {
        mainHandler.post(() -> {
            Throwable actualCause = (throwable instanceof CompletionException) ? throwable.getCause() : throwable;
            String fullMessage = userMessage + (actualCause != null && actualCause.getMessage() != null ? " Detalhes: " + actualCause.getMessage() : null);
            Log.e(TAG, "Erro na operação: " + userMessage, actualCause);
            showErrorMessage(fullMessage);
        });
    }


    private void showErrorMessage(String message) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Erro")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showSuccessMessage(String message) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Sucesso")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}