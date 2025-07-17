package com.app.fragments.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class FormsXgpManejoMelhoramentoAdapter extends RecyclerView.Adapter<FormsXgpManejoMelhoramentoAdapter.FormularioItemViewHolder> {

    private final List<FormsXgpManejoMelhoramentoComponent> dadosFormulario;

    public FormsXgpManejoMelhoramentoAdapter(Context context, List<FormsXgpManejoMelhoramentoComponent> dadosFormulario) {
        this.dadosFormulario = dadosFormulario;
    }

    @NonNull
    @Override
    public FormularioItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_xgp_manejo_melhoramento_form, parent, false);
        return new FormularioItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormularioItemViewHolder holder, int position) {
        holder.bind(dadosFormulario.get(position));
    }

    @Override
    public int getItemCount() {
        return dadosFormulario.size();
    }

    public List<FormsXgpManejoMelhoramentoComponent> getDadosFormularioAtualizados() {
        return dadosFormulario;
    }

    static class FormularioItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView textoCaracteristica;
        private final TextView textoSigla;
        private final EditText campoNota;

        public FormularioItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textoCaracteristica = itemView.findViewById(R.id.nome_caracteristica);
            textoSigla = itemView.findViewById(R.id.sigla_caracteristica);

            TextInputLayout containerNota = itemView.findViewById(R.id.notaContainer);
            if (containerNota != null) {
                campoNota = containerNota.getEditText();
            } else {
                campoNota = null;
            }
        }

        void bind(FormsXgpManejoMelhoramentoComponent item) {
            if (textoCaracteristica != null) {
                textoCaracteristica.setText(item.getCaracteristica());
            }

            if (textoSigla != null) {
                textoSigla.setText(item.getSigla());
            }

            if (campoNota == null) {
                return;
            }

            campoNota.setText(item.getNota() != null ? String.valueOf(item.getNota()) : "");

            campoNota.setOnFocusChangeListener(null);
            campoNota.setOnFocusChangeListener((v, temFoco) -> {
                if (!temFoco) {
                    String textoDigitado = campoNota.getText().toString().trim();
                    try {
                        Integer notaFinal = textoDigitado.isEmpty() ? null : Integer.parseInt(textoDigitado);
                        item.setNota(notaFinal);
                    } catch (NumberFormatException e) {
                        item.setNota(null);
                    }
                }
            });
        }
    }
}