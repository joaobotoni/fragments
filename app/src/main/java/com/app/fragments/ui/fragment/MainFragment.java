package com.app.fragments.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.ui.adapter.FormsAdpter;
import com.app.fragments.ui.components.Forms;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    public MainFragment() {
        super(R.layout.fragment_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        List<Forms> formsList = new ArrayList<>();
        formsList.add(new Forms("Cadastro 1", "C1", "S1", "EXC1"));
        formsList.add(new Forms("Cadastro 2", "C2", "S2", "EXC2"));
        formsList.add(new Forms("Cadastro 3", "C3", "S3", "EXC3"));
        formsList.add(new Forms("Cadastro 4", "C4", "S4", "EXC4"));


        FormsAdpter adapter = new FormsAdpter(requireContext(), formsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }
}
