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
import com.app.fragments.ui.components.FormsXgpManejoMelhoramentoComponents;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class FormsXgpManejoMelhoramentoAdpter extends RecyclerView.Adapter<FormsXgpManejoMelhoramentoAdpter.FormViewHolder> {
    Context context;
    List<FormsXgpManejoMelhoramentoComponents> list;
    public FormsXgpManejoMelhoramentoAdpter(Context context, List<FormsXgpManejoMelhoramentoComponents> list) {
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
        var form = list.get(position);
        holder.bind(form);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FormViewHolder extends RecyclerView.ViewHolder {
        public FormViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        protected void bind(FormsXgpManejoMelhoramentoComponents forms) {
            TextView titleView = itemView.findViewById(R.id.nome_melhoramento);
            titleView.setText(forms.getNomeMelhoramento());
            String nomeMelhoramento = titleView.getText().toString().trim();

            TextInputLayout notaInput = itemView.findViewById(R.id.notaContainer);
            EditText nota = notaInput.getEditText();
            if (nota != null) {
                nota.setHint(forms.getNota());
            }

            TextInputLayout excessaoInput = itemView.findViewById(R.id.excessaoContainer);
            EditText excessao = excessaoInput.getEditText();
            if (excessao != null) {
                excessao.setHint(forms.getExcessao());
            }

            TextInputLayout observacaoInput = itemView.findViewById(R.id.observacaoContainer);
            EditText observacao = observacaoInput.getEditText();
            if (observacao != null) {
                observacao.setHint(forms.getObservacao());
            }
        }
    }
}
