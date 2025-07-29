package com.app.fragments.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.fragments.R;
import com.app.fragments.data.entities.*;
import com.app.fragments.service.ManejoMelhoramentoService;
import com.app.fragments.ui.adapter.XgpManejoMelhoramentoAdapter;
import com.app.fragments.ui.components.XgpManejoMelhoramentoComponent;
import com.google.android.material.button.MaterialButton;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.concurrent.CompletableFuture;

public class XgpManejoMelhoramentoFragment extends Fragment {

    private static final String ARG_MELHORAMENTO_ID = "id_melhoramento";

    private final ManejoMelhoramentoService service;
    private final List<XgpManejoMelhoramentoComponent> components = new ArrayList<>();

    private Long melhoramentoId;
    private RecyclerView recyclerView;
    private XgpManejoMelhoramentoAdapter adapter;

    public XgpManejoMelhoramentoFragment(ManejoMelhoramentoService service) {
        super(R.layout.fragment_xgp_manejo_melhoramento);
        this.service = service;
    }

    public static XgpManejoMelhoramentoFragment newInstance(ManejoMelhoramentoService service, Long melhoramentoId) {
        XgpManejoMelhoramentoFragment fragment = new XgpManejoMelhoramentoFragment(service);
        Bundle args = new Bundle();
        args.putLong(ARG_MELHORAMENTO_ID, melhoramentoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFragment(view);
        loadData();
    }

    private void initializeFragment(View view) {
        extractMelhoramentoId();
        setupViews(view);
        setupRecyclerView();
    }

    private void extractMelhoramentoId() {
        Bundle args = getArguments();
        melhoramentoId = (args != null) ? args.getLong(ARG_MELHORAMENTO_ID, -1L) : -1L;
    }

    private void setupViews(View view) {
        MaterialButton saveButton = view.findViewById(R.id.send);
        saveButton.setOnClickListener(v -> handleSaveClick());
    }

    private void setupRecyclerView() {
        recyclerView = requireView().findViewById(R.id.recyclerViewXgpManejoMelhoramento);
        adapter = new XgpManejoMelhoramentoAdapter(requireContext(), components);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        if (!isValidMelhoramentoId()) {
            showError("ID de melhoramento inválido");
            return;
        }

        loadCharacteristics()
                .thenCompose(this::processCharacteristics)
                .thenRun(this::onDataLoaded)
                .exceptionally(this::handleLoadError);
    }

    private boolean isValidMelhoramentoId() {
        return melhoramentoId != null && melhoramentoId != -1L;
    }

    private CompletableFuture<List<Caracteristica>> loadCharacteristics() {
        return service.getAllCaracteristicasAsync()
                .thenApply(this::filterByMelhoramentoId);
    }

    private List<Caracteristica> filterByMelhoramentoId(List<Caracteristica> characteristics) {
        return characteristics.stream()
                .filter(c -> melhoramentoId.equals(c.getIdMelhoramento()))
                .collect(Collectors.toList());
    }

    private CompletableFuture<Void> processCharacteristics(List<Caracteristica> characteristics) {
        return CompletableFuture.runAsync(() -> {
            List<XgpManejoMelhoramentoComponent> newComponents = convertToComponents(characteristics);
            updateComponents(newComponents);
        });
    }

    private List<XgpManejoMelhoramentoComponent> convertToComponents(List<Caracteristica> characteristics) {
        return characteristics.stream()
                .map(this::createComponent)
                .collect(Collectors.toList());
    }

    private XgpManejoMelhoramentoComponent createComponent(Caracteristica c) {
        return new XgpManejoMelhoramentoComponent(
                c.getIdCaracteristica(),
                c.getDescricao(),
                c.getSigla(),
                c.getTipoDado(),
                null,
                c.getExcessao(),
                c.getNotaInicial(),
                c.getNotaFinal()
        );
    }

    private void updateComponents(List<XgpManejoMelhoramentoComponent> newComponents) {
        components.clear();
        components.addAll(newComponents);
    }

    private void onDataLoaded() {
        runOnUiThread(() -> {
            refreshAdapter();
            handleEmptyData();
        });
    }

    private void refreshAdapter() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void handleEmptyData() {
        if (components.isEmpty()) {
            showInfo("Nenhum dado encontrado para este melhoramento");
        }
    }

    private void handleSaveClick() {
        if (hasValidationErrors()) return;
        saveData();
    }

    private boolean hasValidationErrors() {
        if (hasEmptyFields()) {
            showError("Preencha todos os campos antes de salvar");
            return true;
        }
        return false;
    }

    private boolean hasEmptyFields() {
        return components.stream()
                .anyMatch(c -> isEmptyValue(c.getValorDigitado()));
    }

    private boolean isEmptyValue(String value) {
        return value == null || value.trim().isEmpty();
    }

    private void saveData() {
        List<ManejoMelhoramento> dataToSave = prepareDataForSave();

        service.saveMultipleAsync(dataToSave)
                .thenRun(this::onSaveSuccess)
                .exceptionally(this::handleSaveError);
    }

    private List<ManejoMelhoramento> prepareDataForSave() {
        return components.stream()
                .filter(c -> !isEmptyValue(c.getValorDigitado()))
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    private ManejoMelhoramento convertToEntity(XgpManejoMelhoramentoComponent component) {
        return new ManejoMelhoramento(
                melhoramentoId,
                component.getId(),
                component.getValorDigitado(),
                component.getExcessao(),
                null
        );
    }

    private void onSaveSuccess() {
        showSuccess("Dados salvos com sucesso!");
    }

    private Void handleLoadError(Throwable error) {
        showError("Erro ao carregar dados: " + error.getMessage());
        return null;
    }

    private Void handleSaveError(Throwable error) {
        showError("Erro ao salvar: " + error.getMessage());
        return null;
    }

    private void showSuccess(String message) {
        showDialog("Sucesso", message);
    }

    private void showInfo(String message) {
        showDialog("Informação", message);
    }

    private void showError(String message) {
        showDialog("Erro", message);
    }

    private void showDialog(String title, String message) {
        runOnUiThread(() ->
                new AlertDialog.Builder(requireContext())
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("OK", null)
                        .show()
        );
    }

    private void runOnUiThread(Runnable action) {
        requireActivity().runOnUiThread(action);
    }
}