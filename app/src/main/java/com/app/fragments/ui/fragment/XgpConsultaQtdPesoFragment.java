package com.app.fragments.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.ui.adapter.XgpConsultaQtdPesoAdapter;
import com.app.fragments.ui.components.XgpConsultaQtdPesoComponent;

import java.util.ArrayList;
import java.util.List;

public class XgpConsultaQtdPesoFragment extends Fragment {

    public XgpConsultaQtdPesoFragment() {
        super(R.layout.fragment_consulta_qtd_peso_manejo);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_viexgp_consulta_qtd_peso_menejo);

        List<XgpConsultaQtdPesoComponent> lista = new ArrayList<>();
        lista.add(new XgpConsultaQtdPesoComponent("teste", 12, 480.0, 9, 420.0, 900.0));
        lista.add(new XgpConsultaQtdPesoComponent("goku", 7, 500.0, 11, 430.0, 930.0));
        lista.add(new XgpConsultaQtdPesoComponent("Nelore", 10, 470.0, 8, 415.0, 885.0));

        XgpConsultaQtdPesoAdapter adapter = new XgpConsultaQtdPesoAdapter(requireContext(), lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }
}
