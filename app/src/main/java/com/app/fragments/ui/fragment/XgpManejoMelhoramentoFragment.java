package com.app.fragments.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView; // Importação não utilizada, será removida

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.data.entities.Caracteristica;
import com.app.fragments.service.ManejoMelhoramentoService;
import com.app.fragments.ui.adapter.FormsXgpManejoMelhoramentoAdapter;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;

import java.util.List;
import java.util.stream.Collectors;

public class XgpManejoMelhoramentoFragment extends Fragment {
    private RecyclerView recyclerView;
    private ManejoMelhoramentoService service;
    public XgpManejoMelhoramentoFragment(ManejoMelhoramentoService service) {
        super(R.layout.fragment_xgp_manejo_melhoramento);
        this.service = service;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadData();
    }

    private void loadData() {
        service.getAllCaracteristicasAsync()
                .thenAccept(this::setupRecyclerView)
                .exceptionally(this::handleError);
    }

    private void setupRecyclerView(List<Caracteristica> caracteristicas) {
        List<FormsXgpManejoMelhoramentoComponent> formComponents = caracteristicas.stream()
                .map(c -> new FormsXgpManejoMelhoramentoComponent(
                        c.getIdCaracteristica(), c.getDescricao(), c.getSigla(), null))
                .collect(Collectors.toList());

        requireActivity().runOnUiThread(() -> {
            recyclerView.setAdapter(new FormsXgpManejoMelhoramentoAdapter(requireContext(), formComponents));
        });
    }
    private Void handleError(Throwable throwable) {
        String message = throwable.getCause() != null ? throwable.getCause().getMessage() : "Unexpected error";
        Log.e("Manejo Melhoramento", message, throwable);

        requireActivity().runOnUiThread(() ->
                new AlertDialog.Builder(requireContext())
                        .setTitle("Error").setMessage(message)
                        .setPositiveButton("OK", null)
                        .show()
        );
        return null;
    }
}
