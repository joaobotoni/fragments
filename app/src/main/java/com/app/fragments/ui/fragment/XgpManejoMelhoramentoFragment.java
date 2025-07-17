package com.app.fragments.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class XgpManejoMelhoramentoFragment extends Fragment {

    private static final String TAG = "ManejoMelhoramentoFragment";
    private static final String ARG_ID_MELHORAMENTO = "id_melhoramento";

    // Views
    private RecyclerView recyclerViewCaracteristicas;
    private MaterialButton botaoSalvarManejos;
    private FormsXgpManejoMelhoramentoAdapter adapterManejos;

    // Data
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
        inicializarViews(view);
        configurarRecyclerView();
        configurarListeners();
        carregarCaracteristicas();
    }
    private Long extrairIdDosArgumentos() {
        if (getArguments() == null) return null;
        long id = getArguments().getLong(ARG_ID_MELHORAMENTO, -1L);
        return id == -1L ? null : id;
    }

    private void inicializarViews(View view) {
        recyclerViewCaracteristicas = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);
        botaoSalvarManejos = view.findViewById(R.id.send);
    }

    private void configurarRecyclerView() {
        adapterManejos = new FormsXgpManejoMelhoramentoAdapter(requireContext(), componentesFormularioManejos);
        recyclerViewCaracteristicas.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewCaracteristicas.setAdapter(adapterManejos);
    }

    private void configurarListeners() {
        botaoSalvarManejos.setOnClickListener(v -> processarSalvamento());
    }

    private void carregarCaracteristicas() {
        manejoMelhoramentoService.getAllCaracteristicasAsync()
                .thenAccept(this::processarCaracteristicasCarregadas)
                .exceptionally(this::tratarErroCarregamento);
    }

    private void processarCaracteristicasCarregadas(List<Caracteristica> caracteristicas) {
        executarNaThreadPrincipal(() -> {
            atualizarListaCaracteristicas(caracteristicas);
            criarComponentesFormulario();
            notificarAdapterAtualizado();
            verificarCaracteristicasVazias();
        });
    }

    private void atualizarListaCaracteristicas(List<Caracteristica> caracteristicas) {
        listaCaracteristicas.clear();
        listaCaracteristicas.addAll(caracteristicas);
    }

    private void criarComponentesFormulario() {
        componentesFormularioManejos.clear();
        componentesFormularioManejos.addAll(
                listaCaracteristicas.stream()
                        .filter(this::isCaracteristicaValida)
                        .map(this::criarComponenteFormulario)
                        .collect(Collectors.toList())
        );
    }

    private boolean isCaracteristicaValida(Caracteristica caracteristica) {
        return idMelhoramentoReferencia == null ||
                Objects.equals(caracteristica.getIdMelhoramento(), idMelhoramentoReferencia);
    }

    private FormsXgpManejoMelhoramentoComponent criarComponenteFormulario(Caracteristica caracteristica) {
        return new FormsXgpManejoMelhoramentoComponent(
                caracteristica.getIdCaracteristica(),
                caracteristica.getDescricao(),
                caracteristica.getSigla(),
                null
        );
    }

    private void notificarAdapterAtualizado() {
        adapterManejos.notifyDataSetChanged();
    }

    private void verificarCaracteristicasVazias() {
        if (componentesFormularioManejos.isEmpty()) {
            exibirToast("Nenhuma característica encontrada.");
        }
    }

    private Void tratarErroCarregamento(Throwable throwable) {
        executarNaThreadPrincipal(() -> exibirErro(throwable));
        return null;
    }
    private void processarSalvamento() {
        List<FormsXgpManejoMelhoramentoComponent> componentes = obterComponentesParaSalvar();

        if (!validarComponentesParaSalvar(componentes)) return;

        iniciarProcessoSalvamento();
        salvarManejos(componentes);
    }

    private List<FormsXgpManejoMelhoramentoComponent> obterComponentesParaSalvar() {
        return new ArrayList<>(adapterManejos.getComponents());
    }

    private boolean validarComponentesParaSalvar(List<FormsXgpManejoMelhoramentoComponent> componentes) {
        if (componentes.isEmpty()) {
            exibirToast("Nenhum campo foi preenchido para salvar.");
            return false;
        }
        return true;
    }

    private void iniciarProcessoSalvamento() {
        alterarEstadoBotaoSalvar(false, "Salvando...");
    }

    private void salvarManejos(List<FormsXgpManejoMelhoramentoComponent> componentes) {
        List<ManejoMelhoramento> manejos = criarManejosParaSalvar(componentes);

        manejoMelhoramentoService.saveMultipleAsync(manejos)
                .thenAccept(this::processarSalvamentoSucesso)
                .exceptionally(this::processarErroSalvamento);
    }

    private List<ManejoMelhoramento> criarManejosParaSalvar(List<FormsXgpManejoMelhoramentoComponent> componentes) {
        return componentes.stream()
                .map(this::criarManejoMelhoramento)
                .collect(Collectors.toList());
    }

    private ManejoMelhoramento criarManejoMelhoramento(FormsXgpManejoMelhoramentoComponent componente) {
        Long idCaracteristica = componente.getId();
        Long idMelhoramento = obterIdMelhoramentoPorCaracteristica(idCaracteristica);

        return new ManejoMelhoramento(
                gerarIdUnico(idCaracteristica),
                idMelhoramento,
                idCaracteristica,
                componente.getNota(),
                null
        );
    }

    private Long obterIdMelhoramentoPorCaracteristica(Long idCaracteristica) {
        return listaCaracteristicas.stream()
                .filter(caracteristica -> Objects.equals(caracteristica.getIdCaracteristica(), idCaracteristica))
                .map(Caracteristica::getIdMelhoramento)
                .findFirst()
                .orElse(obterIdMelhoramentoPadrao());
    }

    private Long obterIdMelhoramentoPadrao() {
        return idMelhoramentoReferencia != null ? idMelhoramentoReferencia : 1L;
    }

    private Long gerarIdUnico(Long idCaracteristica) {
        return System.currentTimeMillis() + idCaracteristica;
    }

    private void processarSalvamentoSucesso(List<Long> idsSalvos) {
        executarNaThreadPrincipal(() -> {
            finalizarProcessoSalvamento();
            exibirMensagemSucesso(idsSalvos.size());
        });
    }

    private Void processarErroSalvamento(Throwable throwable) {
        executarNaThreadPrincipal(() -> {
            finalizarProcessoSalvamento();
            exibirErro(throwable);
        });
        return null;
    }

    private void finalizarProcessoSalvamento() {
        alterarEstadoBotaoSalvar(true, "Salvar Manejos");
    }

    private void exibirMensagemSucesso(int quantidadeItens) {
        exibirToast("Dados salvos com sucesso! (" + quantidadeItens + " itens)");
    }

    private void alterarEstadoBotaoSalvar(boolean habilitado, String texto) {
        botaoSalvarManejos.setEnabled(habilitado);
        botaoSalvarManejos.setText(texto);
    }

    private void exibirToast(String mensagem) {
        Toast.makeText(requireContext(), mensagem, Toast.LENGTH_LONG).show();
    }

    private void exibirErro(Throwable throwable) {
        String mensagemErro = extrairMensagemErro(throwable);
        Log.e(TAG, "Erro: " + mensagemErro, throwable);

        new AlertDialog.Builder(requireContext())
                .setTitle("Erro")
                .setMessage(mensagemErro)
                .setPositiveButton("OK", null)
                .show();
    }

    private String extrairMensagemErro(Throwable throwable) {
        if (throwable.getCause() != null && throwable.getCause().getMessage() != null) {
            return throwable.getCause().getMessage();
        }
        return throwable.getMessage() != null ? throwable.getMessage() : "Erro inesperado";
    }

    private void executarNaThreadPrincipal(Runnable acao) {
        if (isAdded() && getActivity() != null) {
            getActivity().runOnUiThread(acao);
        }
    }
}