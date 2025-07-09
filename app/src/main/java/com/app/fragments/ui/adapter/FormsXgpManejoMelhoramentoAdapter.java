package com.app.fragments.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponent;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class FormsXgpManejoMelhoramentoAdapter extends RecyclerView.Adapter<FormsXgpManejoMelhoramentoAdapter.FormViewHolder> {

    private final Context context;
    private final List<FormsXgpManejoMelhoramentoComponent> list;

    public FormsXgpManejoMelhoramentoAdapter(Context context, List<FormsXgpManejoMelhoramentoComponent> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_xgp_manejo_melhoramento_form, parent, false);
        return new FormViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class FormViewHolder extends RecyclerView.ViewHolder {
        private final TextView nome, sigla;
        private final EditText nota;
        public FormViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome_melhoramento);
            sigla = itemView.findViewById(R.id.sigla_melhoramento);
            nota = ((TextInputLayout) itemView.findViewById(R.id.notaContainer)).getEditText();
        }

        void bind(FormsXgpManejoMelhoramentoComponent item) {
            if (nome != null) {
                nome.setText(item.getNomeMelhoramento());
            }
            else {
                Toast.makeText(itemView.getContext(), "Erro: TextView 'nome_melhoramento'", Toast.LENGTH_SHORT).show();
            }
            if (sigla != null) {
                sigla.setText(item.getSigla());
            }
            else {
                Toast.makeText(itemView.getContext(), "Erro: TextView 'sigla_melhoramento'", Toast.LENGTH_SHORT).show();
            }
            if (nota != null) {
                nota.setHint("Digite a nota");
            } else {
                Toast.makeText(itemView.getContext(), "Erro: EditText para 'nota'", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
