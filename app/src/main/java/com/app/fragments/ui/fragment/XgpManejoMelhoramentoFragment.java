package com.app.fragments.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.data.repository.XgpManejoMelhoramentoRepository;
import com.app.fragments.ui.adapter.FormsXgpManejoMelhoramentoAdapter;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;

import java.util.ArrayList;
import java.util.List;

public class XgpManejoMelhoramentoFragment extends Fragment {

    public XgpManejoMelhoramentoFragment() {
        super(R.layout.fragment_xgp_manejo_melhoramento);
    }

    private XgpManejoMelhoramentoRepository repository;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);

        List<FormsXgpManejoMelhoramentoComponent> formsList = new ArrayList<>();

        formsList.add(new FormsXgpManejoMelhoramentoComponent(null, null, null, null));

        FormsXgpManejoMelhoramentoAdapter adapter = new FormsXgpManejoMelhoramentoAdapter(requireContext(), formsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }

}
