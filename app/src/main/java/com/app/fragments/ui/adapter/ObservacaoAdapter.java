package com.app.fragments.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.app.fragments.R;
import com.app.fragments.ui.components.ObservacaoComponent;

import java.util.List;

public class ObservacaoAdapter extends ArrayAdapter<ObservacaoComponent> {
    public ObservacaoAdapter(@NonNull Context context, List<ObservacaoComponent> componentList) {
        super(context, 0, componentList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.observacao_item, parent, false);
        }
        TextView observacaoItem = convertView.findViewById(R.id.observacao);
        ObservacaoComponent item = getItem(position);
        if (item != null) {
            observacaoItem.setText(item.getObservacao().toUpperCase());
        }
        return convertView;
    }
}
