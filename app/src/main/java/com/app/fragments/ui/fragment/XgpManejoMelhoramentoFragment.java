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
import com.app.fragments.data.entities.Observacao;
import com.app.fragments.service.ManejoMelhoramentoService;
import com.app.fragments.ui.adapter.FormsXgpManejoMelhoramentoAdapter;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class XgpManejoMelhoramentoFragment extends Fragment {

    private static final String TAG = "ManejoMelhoramentoFragment";
    private static final String ARG_ID_MELHORAMENTO = "id_melhoramento";

    // Views
    private RecyclerView recyclerViewCaracteristicas;
    private MaterialButton botaoSalvarManejos;
    private TextView nomeMelhoramentoTextView;

    // Adapter e dados
    private FormsXgpManejoMelhoramentoAdapter adapterManejos;
    private final List<FormsXgpManejoMelhoramentoComponent> componentesFormularioManejos = new ArrayList<>();
    private final List<Caracteristica> listaCaracteristicas = new ArrayList<>();
    private final Set<String> observacoesValidas = new HashSet<>();

    // Serviço e ID
    private final ManejoMelhoramentoService manejoMelhoramentoService;
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
        initViews(view);
        setupRecyclerView();
        setupListeners();
        carregarDadosIniciais();
    }

    private void initViews(View view) {
        recyclerViewCaracteristicas = view.findViewById(R.id.recyclerVieXgpManejoMelhoramento);
        botaoSalvarManejos = view.findViewById(R.id.send);
        nomeMelhoramentoTextView = view.findViewById(R.id.nome_melhoramento);
    }

    private Long extrairIdDosArgumentos() {
        if (getArguments() == null) return null;
        return getArguments().getLong(ARG_ID_MELHORAMENTO, -1L);
    }

    private void setupRecyclerView() {
        adapterManejos = new FormsXgpManejoMelhoramentoAdapter(requireContext(), componentesFormularioManejos);
        recyclerViewCaracteristicas.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewCaracteristicas.setAdapter(adapterManejos);
    }

    private void setupListeners() {
        botaoSalvarManejos.setOnClickListener(v -> processarSalvamento());
    }

    private void carregarDadosIniciais() {
        carregarNomeMelhoramento();
        carregarCaracteristicas();
        carregarObservacoesValidas();
    }

    private void carregarNomeMelhoramento() {
        if (idMelhoramentoReferencia == null || idMelhoramentoReferencia == -1L) {
            nomeMelhoramentoTextView.setText("ID do Melhoramento não fornecido");
            return;
        }

        manejoMelhoramentoService.getMelhoramentoByIdAsync(idMelhoramentoReferencia)
                .thenAccept(melhoramento -> executarNaThreadPrincipal(() -> {
                    if (melhoramento != null && melhoramento.getNome() != null) {
                        nomeMelhoramentoTextView.setText(melhoramento.getNome());
                    } else {
                        nomeMelhoramentoTextView.setText("Melhoramento não encontrado");
                    }
                }))
                .exceptionally(throwable -> {
                    executarNaThreadPrincipal(() -> {
                        nomeMelhoramentoTextView.setText("Erro ao carregar");
                        exibirErro(throwable);
                    });
                    return null;
                });
    }

    private void carregarCaracteristicas() {
        manejoMelhoramentoService.getAllCaracteristicasAsync()
                .thenAccept(caracteristicas -> executarNaThreadPrincipal(() -> {
                    listaCaracteristicas.clear();
                    listaCaracteristicas.addAll(caracteristicas);
                    atualizarComponentesFormulario();
                }))
                .exceptionally(throwable -> {
                    executarNaThreadPrincipal(() -> exibirErro(throwable));
                    return null;
                });
    }

    private void carregarObservacoesValidas() {
        manejoMelhoramentoService.getAllObservacoesAsync()
                .thenAccept(observacoes -> executarNaThreadPrincipal(() -> {
                    observacoesValidas.clear();
                    for (Observacao obs : observacoes) {
                        observacoesValidas.add(obs.getDescricao().trim().toLowerCase());
                    }
                }))
                .exceptionally(throwable -> {
                    executarNaThreadPrincipal(() -> exibirErro(throwable));
                    return null;
                });
    }

    private void atualizarComponentesFormulario() {
        componentesFormularioManejos.clear();
        componentesFormularioManejos.addAll(
                listaCaracteristicas.stream()
                        .filter(caracteristica ->
                                idMelhoramentoReferencia == null || idMelhoramentoReferencia == -1L ||
                                        Objects.equals(caracteristica.getIdMelhoramento(), idMelhoramentoReferencia))
                        .map(caracteristica -> new FormsXgpManejoMelhoramentoComponent(
                                caracteristica.getIdCaracteristica(),
                                caracteristica.getDescricao(),
                                caracteristica.getSigla(),
                                null, 
                                caracteristica.getExcessao(),
                                caracteristica.getIsObservacao().equalsIgnoreCase("s"),
                                caracteristica.getNotaInicial(),
                                caracteristica.getNotaFinal()
                        ))
                        .collect(Collectors.toList())
        );

        adapterManejos.notifyDataSetChanged();

        if (componentesFormularioManejos.isEmpty()) {
            Toast.makeText(requireContext(), "Nenhuma característica encontrada.", Toast.LENGTH_LONG).show();
        }
    }

    private void processarSalvamento() {
        List<FormsXgpManejoMelhoramentoComponent> componentes = new ArrayList<>(adapterManejos.getComponents());

        if (componentes.isEmpty()) {
            Toast.makeText(requireContext(), "Nenhum campo foi preenchido para salvar.", Toast.LENGTH_LONG).show();
            return;
        }

        if (!validarDados(componentes)) {
            return;
        }

        desativarBotaoSalvar();

        List<ManejoMelhoramento> manejos = criarListaManejos(componentes);

        manejoMelhoramentoService.saveMultipleAsync(manejos)
                .thenAccept(result -> executarNaThreadPrincipal(() -> {
                    ativarBotaoSalvar();
                    exibirMensagemSucesso(manejos.size());
                }))
                .exceptionally(throwable -> {
                    executarNaThreadPrincipal(() -> {
                        ativarBotaoSalvar();
                        exibirErro(throwable);
                    });
                    return null;
                });
    }

    private boolean validarDados(List<FormsXgpManejoMelhoramentoComponent> componentes) {
        for (FormsXgpManejoMelhoramentoComponent componente : componentes) {
            Caracteristica caracteristica = encontrarCaracteristica(componente.getId());

            if (caracteristica == null) {
                exibirErro("Erro interno: Característica não encontrada para ID " + componente.getId());
                return false;
            }

            if (componente.isEhObservacao()) {
                if (!validarObservacao(componente)) {
                    return false;
                }
            } else {
                if (!validarValor(componente, caracteristica)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validarObservacao(FormsXgpManejoMelhoramentoComponent componente) {
        String observacaoDigitada = componente.getValorDigitado();

        if (observacaoDigitada == null || observacaoDigitada.trim().isEmpty()) {
            return true;
        }

        String[] palavras = observacaoDigitada.trim().toLowerCase().split("\\s+");
        boolean observacaoEncontrada = false;
        for (String palavra : palavras) {
            if (observacoesValidas.contains(palavra)) {
                observacaoEncontrada = true;
                break;
            }
        }

        if (!observacaoEncontrada) {
            exibirErro("Observação inválida para '" + componente.getCaracteristica() + "'. Por favor, use termos válidos.");
            return false;
        }

        return true;
    }

    private boolean validarValor(FormsXgpManejoMelhoramentoComponent componente, Caracteristica caracteristica) {
        String valorDigitado = componente.getValorDigitado();

        if (valorDigitado == null || valorDigitado.trim().isEmpty()) {
            exibirErro("A nota para '" + componente.getCaracteristica() + "' não pode estar vazia.");
            return false;
        }
        Integer valor = null;
        try {
            valor = Integer.parseInt(valorDigitado);
        } catch (NumberFormatException e) {
            exibirErro("A nota para '" + componente.getCaracteristica() + "' deve ser um número inteiro válido.");
            return false;
        }

        if (caracteristica.getNotaInicial() != null && caracteristica.getNotaFinal() != null) {
            if (valor < caracteristica.getNotaInicial() || valor > caracteristica.getNotaFinal()) {
                exibirErro("A nota para '" + componente.getCaracteristica() + "' deve estar entre " +
                        caracteristica.getNotaInicial() + " e " + caracteristica.getNotaFinal() + ".");
                return false;
            }
        } else {
            Log.w(TAG, "Limites de nota não definidos para a característica: " + caracteristica.getDescricao());
        }
        return true;
    }

    private Caracteristica encontrarCaracteristica(Long idCaracteristica) {
        return listaCaracteristicas.stream()
                .filter(c -> Objects.equals(c.getIdCaracteristica(), idCaracteristica))
                .findFirst()
                .orElse(null);
    }

    private List<ManejoMelhoramento> criarListaManejos(List<FormsXgpManejoMelhoramentoComponent> componentes) {
        return componentes.stream()
                .map(c -> {
                    Long idMelhoramento = encontrarIdMelhoramento(c.getId());
                    return new ManejoMelhoramento(
                            System.currentTimeMillis() + c.getId(),
                            idMelhoramento,
                            c.getId(),
                            c.getValorDigitado(),
                            c.getExcessao(),
                            c.isEhObservacao() ? c.getValorDigitado() : null
                    );
                })
                .collect(Collectors.toList());
    }

    private Long encontrarIdMelhoramento(Long idCaracteristica) {
        return listaCaracteristicas.stream()
                .filter(c -> Objects.equals(c.getIdCaracteristica(), idCaracteristica))
                .map(Caracteristica::getIdMelhoramento)
                .findFirst()
                .orElse(idMelhoramentoReferencia != null ? idMelhoramentoReferencia : 1L);
    }

    private void desativarBotaoSalvar() {
        botaoSalvarManejos.setEnabled(false);
        botaoSalvarManejos.setText("Salvando...");
    }

    private void ativarBotaoSalvar() {
        botaoSalvarManejos.setEnabled(true);
        botaoSalvarManejos.setText("SALVAR");
    }

    private void exibirMensagemSucesso(int itensSalvos) {
        Toast.makeText(requireContext(),
                "Dados salvos com sucesso (" + itensSalvos + " itens)",
                Toast.LENGTH_LONG).show();
    }

    private void exibirErro(Throwable throwable) {
        String mensagem = throwable.getCause() != null ?
                throwable.getCause().getMessage() :
                throwable.getMessage() != null ?
                        throwable.getMessage() : "Erro desconhecido";

        exibirErro(mensagem);
    }

    private void exibirErro(String mensagem) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Erro")
                .setMessage(mensagem)
                .setPositiveButton("OK", null)
                .show();
    }

    private void executarNaThreadPrincipal(Runnable acao) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(acao);
        }
    }
}
