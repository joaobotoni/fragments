package com.app.fragments.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class XgpManejoMelhoramentoFragment extends Fragment {

    private final ManejoMelhoramentoService manejoService;
    private RecyclerView recyclerView;
    private AutoCompleteTextView observationInput;
    private ObservacaoComponent selectedObsComponent;
    private long idMelhoramento;

    private XgpManejoMelhoramentoFragment(ManejoMelhoramentoService service) {
        super(R.layout.fragment_xgp_manejo_melhoramento);
        this.manejoService = service;
    }

    public static XgpManejoMelhoramentoFragment newInstance(long idMelhoramento, ManejoMelhoramentoService service) {
        XgpManejoMelhoramentoFragment fragment = new XgpManejoMelhoramentoFragment(service);
        Bundle args = new Bundle();
        args.putLong("idMelhoramento", idMelhoramento);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idMelhoramento = getArguments().getLong("idMelhoramento");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);
        observationInput = view.findViewById(R.id.spinner_manejo_melhoramento);
        TextView nomeMelhoramento = view.findViewById(R.id.nome_melhoramento);
        nomeMelhoramento.setText(setupMelhoramento(nomeMelhoramento));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadData();
    }

    private void loadData() {
        CompletableFuture.allOf(
                manejoService.getAllCaracteristicasAsync().thenAccept(this::setupForms),
                manejoService.getAllObservacoesAsync().thenAccept(this::setupObservations)
        ).exceptionally(this::handleError);
    }

    private String setupMelhoramento(TextView editText) {
        Melhoramento melhoramento = manejoService.getMelhoramentoByIdAsync(idMelhoramento).join();
        assert melhoramento != null : handleError(new Throwable("Erro so setup de melhoramento"));
        return melhoramento.getNome();
    }

    private void setupForms(List<Caracteristica> caracteristicas) {
        List<FormsXgpManejoMelhoramentoComponent> forms = caracteristicas.stream()
                .map(c -> new FormsXgpManejoMelhoramentoComponent(c.getIdCaracteristica(), c.getDescricao(), c.getSigla(), null))
                .collect(Collectors.toList());

        requireActivity().runOnUiThread(() ->
                recyclerView.setAdapter(new FormsXgpManejoMelhoramentoAdapter(requireContext(), forms))
        );
    }

    private void setupObservations(List<Observacao> observacoes) {
        List<ObservacaoComponent> components = observacoes.stream()
                .map(o -> new ObservacaoComponent(o.getIdObservacao(), o.getSigla()))
                .collect(Collectors.toList());

        requireActivity().runOnUiThread(() -> {
            observationInput.setAdapter(new ObservacaoAdapter(requireContext(), components));
            observationInput.setOnItemClickListener((parent, view, position, id) ->
                    selectedObsComponent = (ObservacaoComponent) parent.getItemAtPosition(position)
            );
        });
    }

    private Void handleError(Throwable throwable) {
        String message = throwable.getCause() != null ? throwable.getCause().getMessage() : "Erro inesperado";
        Log.e("XgpFragment", message, throwable);

        requireActivity().runOnUiThread(() -> new AlertDialog.Builder(requireContext())
                .setTitle("Erro")
                .setMessage(message)
                .setPositiveButton("OK", null).show());
        return null;
    }
}