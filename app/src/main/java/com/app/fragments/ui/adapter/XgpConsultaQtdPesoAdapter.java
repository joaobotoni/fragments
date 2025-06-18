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
    private final List<XgpConsultaQtdPesoComponent> xgpConsultaQtdPesoComponentList;

    public XgpConsultaQtdPesoAdapter(Context context, List<XgpConsultaQtdPesoComponent> list) {
        this.context = context;
        this.xgpConsultaQtdPesoComponentList = list;
    }

    @NonNull
    @Override
    public ViewHolderConsulta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_consulta_qtd_peso_manejo, parent, false);
        return new ViewHolderConsulta(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderConsulta holder, int position) {
        XgpConsultaQtdPesoComponent component = xgpConsultaQtdPesoComponentList.get(position);
        holder.bind(component);
    }

    @Override
    public int getItemCount() {
        return xgpConsultaQtdPesoComponentList != null ? xgpConsultaQtdPesoComponentList.size() : 0;
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
            nomeTouroPaiView.setText("Touro: " + component.getNomeTouroPai());
            machoQuantidadeView.setText("Qnt: " + component.getMachoQuantidade());
            machoPesoView.setText("Peso: " + component.getMachoPeso() + "kg");
            femeaQuantidadeView.setText("Qnt: " + component.getFemeaQuantidade());
            femeaPesoView.setText("Peso: " + component.getFemeaPeso() + "kg");
            pesoTotalView.setText("Peso: " + component.getTotal() + "kg");
        }
    }
}
