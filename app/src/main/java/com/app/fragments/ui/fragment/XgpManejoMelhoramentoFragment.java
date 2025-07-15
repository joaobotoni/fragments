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

public class XgpManejoMelhoramentoFragment extends Fragment {

    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final ManejoMelhoramentoService manejoService;

    private RecyclerView recyclerView;
    private AutoCompleteTextView observationInput;
    private MaterialButton saveButton;

    private ObservacaoComponent selectedObsComponent;
    private long idMelhoramento;

    public XgpManejoMelhoramentoFragment(ManejoMelhoramentoService service) {
        super(R.layout.fragment_xgp_manejo_melhoramento);
        this.manejoService = service;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);
        observationInput = view.findViewById(R.id.spinner_manejo_melhoramento);
        saveButton = view.findViewById(R.id.send);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadInitialData();
    }

    private void loadInitialData() {
        CompletableFuture<List<Caracteristica>> futCaracteristicas = manejoService.getAllCaracteristicasAsync();
        CompletableFuture<List<Observacao>> futObservacoes = manejoService.getAllObservacoesAsync();

        CompletableFuture.allOf(futCaracteristicas, futObservacoes)
                .thenAcceptAsync(v -> {
                    List<FormsXgpManejoMelhoramentoComponent> forms =
                            mapCaracteristicasToForms(futCaracteristicas.join());
                    List<ObservacaoComponent> observacoes =
                            mapObservacoesToComponents(futObservacoes.join());
                    mainHandler.post(() -> {
                        bindFormsToRecyclerView(forms);
                        bindObservationsToDropdown(observacoes);
                    });
                })
                .exceptionally(this::handleErrorReturn);
    }

    private List<FormsXgpManejoMelhoramentoComponent> mapCaracteristicasToForms(List<Caracteristica> lista) {
        List<FormsXgpManejoMelhoramentoComponent> forms = new ArrayList<>();
        for (Caracteristica c : lista) {
            forms.add(new FormsXgpManejoMelhoramentoComponent(c.getIdCaracteristica(), c.getDescricao(), c.getSigla(), null));
        }
        return forms;
    }

    private List<ObservacaoComponent> mapObservacoesToComponents(List<Observacao> lista) {
        List<ObservacaoComponent> obs = new ArrayList<>();
        for (Observacao o : lista) {
            obs.add(new ObservacaoComponent(o.getIdObservacao(), o.getSigla()));
        }
        return obs;
    }

    private void bindFormsToRecyclerView(List<FormsXgpManejoMelhoramentoComponent> forms) {
        FormsXgpManejoMelhoramentoAdapter adapter =
                new FormsXgpManejoMelhoramentoAdapter(requireContext(), forms);
        recyclerView.setAdapter(adapter);
    }

    private void bindObservationsToDropdown(List<ObservacaoComponent> observacoes) {
        ObservacaoAdapter adapter = new ObservacaoAdapter(requireContext(), observacoes);
        observationInput.setAdapter(adapter);

        observationInput.setOnItemClickListener((parent, view, position, id) ->
                selectedObsComponent = (ObservacaoComponent) parent.getItemAtPosition(position)
        );
    }


    private Void handleErrorReturn(Throwable throwable) {
        Throwable cause = throwable instanceof CompletionException
                ? throwable.getCause()
                : throwable;

        String msg = cause != null ? cause.getMessage() : "Erro inesperado.";
        Log.e(TAG, "Falha ao salvar: " + msg, cause);
        mainHandler.post(() -> showErrorMessage(msg));
        return null;
    }

    private void showErrorMessage(String message) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Erro")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showSuccessMessage() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Sucesso")
                .setMessage("Dados salvos com sucesso!")
                .setPositiveButton("OK", null)
                .show();
    }
}
