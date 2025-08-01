package com.app.fragments.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.widget.TextView;

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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class XgpManejoMelhoramentoFragment extends Fragment {

    private static final String ARG_MELHORAMENTO_ID = "id_melhoramento";

    private final ManejoMelhoramentoService service;
    private final List<XgpManejoMelhoramentoComponent> components = new ArrayList<>();

    private Long melhoramentoId;
    private TextView nomeMelhoramento;
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
        nomeMelhoramento = requireView().findViewById(R.id.nome_melhoramento);
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
                .thenRun(this::loadNameMelhoramento)
                .exceptionally(this::handleLoadError);
    }

    private boolean isValidMelhoramentoId() {
        return melhoramentoId != null && melhoramentoId != -1L;
    }

    private void loadNameMelhoramento(){
        Melhoramento melhoramento =  service.getMelhoramentoByIdAsync(melhoramentoId).join();
        runOnUiThread(() -> {
            if (melhoramento != null) {
                nomeMelhoramento.setText(melhoramento.getNome());
            } else {
                nomeMelhoramento.setText("Melhoramento não encontrado");
            }
        });
    }

    private CompletableFuture<List<Caracteristica>> loadCharacteristics() {
        return service.getAllCaracteristicasAsync()
                .thenApply(this::filterCharacteristicsByMelhoramentoId);
    }

    private List<Caracteristica> filterCharacteristicsByMelhoramentoId(List<Caracteristica> characteristics) {
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
                c.getIdCharacteristic(),
                c.getDescription(),
                c.getSigla(),
                c.getType(),
                null,
                Optional.ofNullable(c.getListOptions()),
                Optional.ofNullable(c.getInitialValue()),
                Optional.ofNullable(c.getFinalValue()),
                c.getIsObservation()
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
            showInfo("Nenhuma característica encontrada para este melhoramento.");
        }
    }

    private void handleSaveClick() {
        if (!isAnyFieldFilled()) {
            showError("Preencha pelo menos um campo antes de salvar.");
            return;
        }

        validateAllFilledComponentsAsync()
                .thenAccept(this::handleValidationResult)
                .exceptionally(this::handleSaveError);
    }

    private boolean isAnyFieldFilled() {
        return components.stream().anyMatch(c -> !isEmptyValue(c.getValue()));
    }

    private void handleValidationResult(boolean allValid) {
        if (allValid) {
            saveData();
        }
    }

    private boolean isEmptyValue(String value) {
        return value == null || value.trim().isEmpty();
    }

    private CompletableFuture<Boolean> validateAllFilledComponentsAsync() {
        List<CompletableFuture<Boolean>> validationFutures = components.stream()
                .filter(c -> !isEmptyValue(c.getValue()))
                .map(this::validateComponentAsync)
                .collect(Collectors.toList());

        return CompletableFuture.allOf(validationFutures.toArray(new CompletableFuture[0]))
                .thenApply(v -> validationFutures.stream().allMatch(CompletableFuture::join));
    }

    private CompletableFuture<Boolean> validateComponentAsync(XgpManejoMelhoramentoComponent component) {
        if (!isObservationField(component)) {
            return CompletableFuture.completedFuture(true);
        }

        return findMatchingObservations(component.getValue())
                .thenApply(matchingObservations -> {
                    if (matchingObservations.isEmpty()) {
                        showError("Valor '" + component.getValue() + "' para '" + component.getCharacteristic() + "' não contém uma observação válida.");
                        return false;
                    }
                    return true;
                });
    }

    private boolean isObservationField(XgpManejoMelhoramentoComponent component) {
        return "s".equalsIgnoreCase(component.getIsObservation());
    }

    private CompletableFuture<List<Observacao>> findMatchingObservations(String value) {
        return service.getAllObservacoesAsync()
                .thenApply(this::filterObservationsByMelhoramentoId)
                .thenApply(observations -> observations.stream()
                        .filter(o -> containsObservation(value, o.getSigla()))
                        .collect(Collectors.toList()));
    }

    private List<Observacao> filterObservationsByMelhoramentoId(List<Observacao> observations) {
        return observations.stream()
                .filter(o -> melhoramentoId.equals(o.getIdMelhoramento()))
                .collect(Collectors.toList());
    }

    private boolean containsObservation(String value, String sigla) {
        return sigla != null && value != null && value.toLowerCase().contains(sigla.toLowerCase());
    }

    private void saveData() {
        List<ManejoMelhoramento> dataToSave = prepareDataForSave();
        service.saveMultipleAsync(dataToSave)
                .thenRun(this::onSaveSuccess)
                .exceptionally(this::handleSaveError);
    }

    private List<ManejoMelhoramento> prepareDataForSave() {
        return components.stream()
                .filter(c -> !isEmptyValue(c.getValue()))
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    private ManejoMelhoramento convertToEntity(XgpManejoMelhoramentoComponent component) {
        return new ManejoMelhoramento(
                melhoramentoId,
                component.getIdCharacteristic(),
                component.getValue(),
                null,
                isObservationField(component) ? component.getValue() : null
        );
    }

    private void onSaveSuccess() {
        runOnUiThread(() -> showSuccess("Dados salvos com sucesso!"));
    }

    private Void handleLoadError(Throwable error) {
        runOnUiThread(() -> showError("Erro ao carregar dados: " + error.getMessage()));
        return null;
    }

    private Void handleSaveError(Throwable error) {
        runOnUiThread(() -> showError("Erro ao salvar: " + error.getMessage()));
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
        if (isAdded() && getContext() != null) {
            runOnUiThread(() -> new AlertDialog.Builder(requireContext())
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show());
        }
    }

    private void runOnUiThread(Runnable action) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(action);
        }
    }
}