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


public class FormsXgpManejoMelhoramentoAdpter extends RecyclerView.Adapter<FormsXgpManejoMelhoramentoAdpter.FormViewHolder> {

    Context context;
    List<FormsXgpManejoMelhoramentoComponent> list;
    public FormsXgpManejoMelhoramentoAdpter(Context context, List<FormsXgpManejoMelhoramentoComponent> list) {
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

    public class FormViewHolder extends RecyclerView.ViewHolder {
        public FormViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        protected void bind(FormsXgpManejoMelhoramentoComponent forms) {
            TextView titleView = itemView.findViewById(R.id.nome_melhoramento);
            titleView.setText(forms.getNomeMelhoramento());
            String nomeMelhoramento = titleView.getText().toString().trim();

            TextInputLayout notaInput = itemView.findViewById(R.id.notaContainer);
            EditText nota = notaInput.getEditText();
            if (nota != null) {
                nota.setHint(forms.getNota());
            }
        }
    }
}
