package com.app.fragments.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.ui.components.XgpConsultaQtdPesoComponent;

import java.util.List;

public class XgpConsultaQtdPesoAdapter extends RecyclerView.Adapter<XgpConsultaQtdPesoAdapter.ViewHolderConsulta> {

    private final Context context;
    private final List<XgpConsultaQtdPesoComponent> list;

    public XgpConsultaQtdPesoAdapter(Context context, List<XgpConsultaQtdPesoComponent> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderConsulta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_consulta_qtd_peso_manejo, parent, false);
        return new ViewHolderConsulta(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderConsulta holder, int position) {
        XgpConsultaQtdPesoComponent component = list.get(position);
        holder.bind(component);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolderConsulta extends RecyclerView.ViewHolder {

        private final TextView nomeTouroPaiView;
        private final TextView machoQuantidadeView;
        private final TextView machoPesoView;
        private final TextView femeaQuantidadeView;
        private final TextView femeaPesoView;
        private final TextView pesoTotalView;

        public ViewHolderConsulta(@NonNull View itemView) {
            super(itemView);
            nomeTouroPaiView = itemView.findViewById(R.id.nomeTouroPai);
            machoQuantidadeView = itemView.findViewById(R.id.machoQuantidade);
            machoPesoView = itemView.findViewById(R.id.machoPeso);
            femeaQuantidadeView = itemView.findViewById(R.id.femeaQuantidade);
            femeaPesoView = itemView.findViewById(R.id.femeaPeso);
            pesoTotalView = itemView.findViewById(R.id.pesoTotal);
        }
        public void bind(XgpConsultaQtdPesoComponent component) {
            if (nomeTouroPaiView != null) {
                nomeTouroPaiView.setText("Touro: " + component.getNomeTouroPai());
            }
            if (machoQuantidadeView != null) {
                machoQuantidadeView.setText(String.valueOf(component.getMachoQuantidade()));
            }
            if (machoPesoView != null) {
                machoPesoView.setText(component.getMachoPeso() + "kg");
            }
            if (femeaQuantidadeView != null) {
                femeaQuantidadeView.setText(String.valueOf(component.getFemeaQuantidade()));
            }
            if (femeaPesoView != null) {
                femeaPesoView.setText(component.getFemeaPeso() + "kg");
            }
            if (pesoTotalView != null) {
                pesoTotalView.setText("Peso Total: " + component.getTotal() + "kg");
            }
        }
    }
}
