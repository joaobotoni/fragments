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
        FormsXgpManejoMelhoramentoComponent form = list.get(position);
        holder.bind(form);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class FormViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;
        private final EditText notaView;

        public FormViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.nome_melhoramento);
            TextInputLayout notaInput = itemView.findViewById(R.id.notaContainer);
            notaView = notaInput != null ? notaInput.getEditText() : null;
        }
        public void bind(FormsXgpManejoMelhoramentoComponent form) {
            if (titleView != null) {
                titleView.setText(form.getNomeMelhoramento());
            }

            if (notaView != null) {
                notaView.setHint(form.getNota());
            }
        }
    }
}
