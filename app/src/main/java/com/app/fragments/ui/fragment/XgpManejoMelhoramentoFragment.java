package com.app.fragments.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.app.AlertDialog;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.data.entities.Caracteristica;
import com.app.fragments.data.entities.ManejoMelhoramento;
import com.app.fragments.service.ManejoMelhoramentoService;
import com.app.fragments.ui.adapter.FormsXgpManejoMelhoramentoAdapter;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class XgpManejoMelhoramentoFragment extends Fragment {

    private static final String TAG = "ManejoMelhoramentoFragment";
    private static final String ARG_ID_MELHORAMENTO = "id_melhoramento";

    private RecyclerView recyclerViewCaracteristicas;
    private MaterialButton botaoSalvarManejos;
    private TextView nomeMelhoramentoTextView;
    private FormsXgpManejoMelhoramentoAdapter adapterManejos;

    private final ManejoMelhoramentoService manejoMelhoramentoService;
    private final List<FormsXgpManejoMelhoramentoComponent> componentesFormularioManejos = new ArrayList<>();
    private final List<Caracteristica> listaCaracteristicas = new ArrayList<>();

    private Long idMelhoramentoReferencia;

    public XgpManejoMelhoramentoFragment(ManejoMelhoramentoService service) {
        super(R.layout.fragment_xgp_manejo_melhoramento);
        this.manejoMelhoramentoService = service;
    }

    public static XgpManejoMelhoramentoFragment newInstance(ManejoMelhoramentoService service, Long idMelhoramento) {
        XgpManejoMelhoramentoFragment fragment = new XgpManejoMelhoramentoFragment(service);
        Bundle args = new Bundle();
        args.putLong(ARG_ID_MELHORAMENTO, idMelhoramento);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idMelhoramentoReferencia = extrairIdDosArgumentos();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewCaracteristicas = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);
        botaoSalvarManejos = view.findViewById(R.id.send);
        nomeMelhoramentoTextView = view.findViewById(R.id.nome_melhoramento);

        configurarRecyclerView();
        botaoSalvarManejos.setOnClickListener(v -> processarSalvamento());
        carregarDadosIniciais();
    }

    private Long extrairIdDosArgumentos() {
        if (getArguments() == null) return null;
        long id = getArguments().getLong(ARG_ID_MELHORAMENTO, -1L);
        return id == -1L ? null : id;
    }

    private void configurarRecyclerView() {
        adapterManejos = new FormsXgpManejoMelhoramentoAdapter(requireContext(), componentesFormularioManejos);
        recyclerViewCaracteristicas.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewCaracteristicas.setAdapter(adapterManejos);
    }

    private void carregarDadosIniciais() {
        if (idMelhoramentoReferencia != null) {
            manejoMelhoramentoService.getMelhoramentoByIdAsync(idMelhoramentoReferencia)
                    .thenAccept(melhoramento -> executarNaThreadPrincipal(() -> {
                        nomeMelhoramentoTextView.setText(
                                melhoramento != null && melhoramento.getNome() != null
                                        ? melhoramento.getNome()
                                        : "Melhoramento não encontrado"
                        );
                    }))
                    .exceptionally(throwable -> {
                        executarNaThreadPrincipal(() -> {
                            nomeMelhoramentoTextView.setText("Erro ao carregar Melhoramento");
                            exibirErro(throwable);
                        });
                        return null;
                    });
        } else {
            nomeMelhoramentoTextView.setText("ID do Melhoramento não fornecido");
        }

        manejoMelhoramentoService.getAllCaracteristicasAsync()
                .thenAccept(caracteristicas -> executarNaThreadPrincipal(() -> {
                    listaCaracteristicas.clear();
                    listaCaracteristicas.addAll(caracteristicas);

                    componentesFormularioManejos.clear();
                    componentesFormularioManejos.addAll(
                            listaCaracteristicas.stream()
                                    .filter(c -> idMelhoramentoReferencia == null ||
                                            Objects.equals(c.getIdMelhoramento(), idMelhoramentoReferencia))
                                    .map(c -> new FormsXgpManejoMelhoramentoComponent(
                                            c.getIdCaracteristica(),
                                            c.getDescricao(),
                                            c.getSigla(),
                                            null,
                                            c.getExcessao(),
                                            "s".equalsIgnoreCase(c.getIsObservacao()),
                                            c.getNotaInicial(),
                                            c.getNotaFinal()
                                    ))
                                    .collect(Collectors.toList())
                    );

                    adapterManejos.notifyDataSetChanged();

                    if (componentesFormularioManejos.isEmpty()) {
                        Toast.makeText(requireContext(), "Nenhuma característica encontrada.", Toast.LENGTH_LONG).show();
                    }
                }))
                .exceptionally(throwable -> {
                    executarNaThreadPrincipal(() -> exibirErro(throwable));
                    return null;
                });
    }

    private void processarSalvamento() {
        List<FormsXgpManejoMelhoramentoComponent> componentes = new ArrayList<>(adapterManejos.getComponents());

        if (componentes.isEmpty()) {
            Toast.makeText(requireContext(), "Nenhum campo foi preenchido para salvar.", Toast.LENGTH_LONG).show();
            return;
        }

        if (!validarNotasEObservacoes(componentes)) {
            return;
        }

        botaoSalvarManejos.setEnabled(false);
        botaoSalvarManejos.setText("Salvando...");

        List<ManejoMelhoramento> manejos = componentes.stream()
                .map(c -> {
                    Long idCaracteristica = c.getId();
                    Long idMelhoramento = listaCaracteristicas.stream()
                            .filter(car -> Objects.equals(car.getIdCaracteristica(), idCaracteristica))
                            .map(Caracteristica::getIdMelhoramento)
                            .findFirst()
                            .orElse(idMelhoramentoReferencia != null ? idMelhoramentoReferencia : 1L);

                    return new ManejoMelhoramento(
                            System.currentTimeMillis() + idCaracteristica,
                            idMelhoramento,
                            idCaracteristica,
                            c.isEhObservacao() ? null : c.getNota(),
                            c.getExcessao(),
                            c.isEhObservacao() ? c.getObservacao() : null
                    );
                })
                .collect(Collectors.toList());

        manejoMelhoramentoService.saveMultipleAsync(manejos)
                .thenAccept(result -> executarNaThreadPrincipal(() -> {
                    botaoSalvarManejos.setEnabled(true);
                    botaoSalvarManejos.setText("SALVAR");
                    Toast.makeText(requireContext(),
                            "Dados salvos com sucesso! (" + manejos.size() + " itens)",
                            Toast.LENGTH_LONG).show();
                }))
                .exceptionally(throwable -> {
                    executarNaThreadPrincipal(() -> {
                        botaoSalvarManejos.setEnabled(true);
                        botaoSalvarManejos.setText("SALVAR");
                        exibirErro(throwable);
                    });
                    return null;
                });
    }

    private boolean validarNotasEObservacoes(List<FormsXgpManejoMelhoramentoComponent> componentes) {
        for (FormsXgpManejoMelhoramentoComponent c : componentes) {
            Caracteristica caracteristica = listaCaracteristicas.stream()
                    .filter(car -> Objects.equals(car.getIdCaracteristica(), c.getId()))
                    .findFirst()
                    .orElse(null);

            if (caracteristica == null) {
                exibirErro(new Throwable("Característica não encontrada para o componente: " + c.getCaracteristica()));
                return false;
            }

            if (c.isEhObservacao()) {
                if (c.getNota() != null) {
                    exibirErro(new Throwable("Campo de observação não deve ter nota numérica: " + c.getCaracteristica()));
                    return false;
                }
            } else {
                if (c.getNota() == null) {
                    exibirErro(new Throwable("Nota não pode ser vazia para: " + c.getCaracteristica()));
                    return false;
                }
                if (c.getObservacao() != null && !c.getObservacao().isEmpty()) {
                    exibirErro(new Throwable("Campo de nota não deve ter observação: " + c.getCaracteristica()));
                    return false;
                }
                if (caracteristica.getNotaInicial() != null && caracteristica.getNotaFinal() != null &&
                        (c.getNota() < caracteristica.getNotaInicial() || c.getNota() > caracteristica.getNotaFinal())) {
                    exibirErro(new Throwable("Nota para '" + caracteristica.getDescricao() + "' deve estar entre " +
                            caracteristica.getNotaInicial() + " e " + caracteristica.getNotaFinal()));
                    return false;
                }
            }
        }
        return true;
    }

    private void exibirErro(Throwable throwable) {
        String msg = throwable.getCause() != null && throwable.getCause().getMessage() != null ?
                throwable.getCause().getMessage() :
                throwable.getMessage() != null ? throwable.getMessage() : "Erro inesperado";

        Log.e(TAG, "Erro: " + msg, throwable);
        new AlertDialog.Builder(requireContext())
                .setTitle("Erro")
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }

    private void executarNaThreadPrincipal(Runnable acao) {
        if (isAdded() && getActivity() != null) {
            getActivity().runOnUiThread(acao);
        }
    }
}
