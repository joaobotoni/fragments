package com.app.fragments.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.ui.adapter.FormsXgpManejoMelhoramentoAdapter;
import com.app.fragments.ui.adapter.ObservacaoAdapter;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;
import com.app.fragments.ui.components.ObservacaoComponent;

import java.util.ArrayList;
import java.util.List;

public class XgpManejoMelhoramentoFragment extends Fragment {

    public XgpManejoMelhoramentoFragment() {
        super(R.layout.fragment_xgp_manejo_melhoramento);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);
        AutoCompleteTextView observacaoSpinner = view.findViewById(R.id.spinner_manejo_melhoramento);

        List<FormsXgpManejoMelhoramentoComponent> formsList = new ArrayList<>();
        formsList.add(new FormsXgpManejoMelhoramentoComponent("PMGZ","SGA","Digite a nota"));
        formsList.add(new FormsXgpManejoMelhoramentoComponent("GENEPLUS","SGT","Digite a nota"));
        formsList.add(new FormsXgpManejoMelhoramentoComponent("GENCIS","SGG","Digite a nota"));
        formsList.add(new FormsXgpManejoMelhoramentoComponent("QUALITAS","SGI","Digite a nota"));

        List<ObservacaoComponent> components = new ArrayList<>();
        components.add(new ObservacaoComponent("EP (Excesso de Peso)"));
        components.add(new ObservacaoComponent("DP (Dados Ponderados)"));
        components.add(new ObservacaoComponent("ATF (Animal Testado Fora do Padrão)"));

        ObservacaoAdapter observacaoAdapter = new ObservacaoAdapter(requireContext(), components);
        observacaoSpinner.setAdapter(observacaoAdapter);

        FormsXgpManejoMelhoramentoAdapter adapter = new FormsXgpManejoMelhoramentoAdapter(requireContext(), formsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        observacaoSpinner.setOnItemClickListener((parent, itemView, position, id) -> {
            ObservacaoComponent selecionado = (ObservacaoComponent) parent.getItemAtPosition(position);
        });
    }
}
